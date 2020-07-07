package com.empathy.api.service.board;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.api.dto.board.SprintSummary;
import com.empathy.model.project.sprint.BacklogIssue;
import com.empathy.model.project.sprint.IssueTeamMember;
import com.empathy.model.project.sprint.IssueTeamMemberBacklog;
import com.empathy.model.project.sprint.Sprint;
import com.empathy.model.project.sprint.SprintId;
import com.empathy.repository.project.sprint.BacklogIssueRepository;
import com.empathy.repository.project.sprint.IssueTeamMemberBacklogRepository;
import com.empathy.repository.project.sprint.IssueTeamMemberRepository;
import com.empathy.repository.project.sprint.SprintRepository;
import com.empathy.util.DateTime;

@Service
public class BoardService implements IBoardService {

	Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private BacklogIssueRepository backlogIssueRepository;

	@Autowired
	private IssueTeamMemberRepository issueTeamMemberRepository;

	@Autowired
	private IssueTeamMemberBacklogRepository issueTeamMemberBacklogRepository;

	@Override
	public SprintSummary findById(SprintId sprintID) throws Exception {

		Optional<Sprint> oSprint = sprintRepository.findById(sprintID);

		if (!oSprint.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Sprint sprint = oSprint.get();

		SprintSummary sprintSummary = new SprintSummary();
		sprintSummary.setName(sprint.getName());
		sprintSummary.setDescription(sprint.getDescription());
		sprintSummary.setStartDate(sprint.getStartDate());
		sprintSummary.setEndDate(sprint.getEndDate());
		double progress = this.calculateProgress(sprint.getStartDate(), sprint.getEndDate());
		sprintSummary.getMetaData().put("progress", progress);
		return sprintSummary;
	}

	@Override
	public List<BacklogIssue> findBacklogBySprintId(String sprintID) {

		List<BacklogIssue> backlogIssue = new ArrayList<BacklogIssue>();
		backlogIssue = backlogIssueRepository.findBySprintId(sprintID);

		for (BacklogIssue i : backlogIssue) {
			double progress = this.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetadaData("progress", Math.round(progress));
		}
		return backlogIssue;
	}

	private double calculateProgress(Date date1, Date date2) {
		// calculate progress
		LocalDateTime startDate = DateTime.convertToLocalDateTimeViaSqlTimestamp(date1);
		LocalDateTime endDate = DateTime.convertToLocalDateTimeViaSqlTimestamp(date2);
		LocalDateTime now = LocalDateTime.now();
		long totalDays = startDate.until(endDate, ChronoUnit.DAYS);
		long elapsedDays = startDate.until(now, ChronoUnit.DAYS);
		logger.debug("totalDays: {}, elapsedDays: {}", totalDays, elapsedDays);
		if (totalDays == 0 || totalDays < 0) {
			StringBuilder strBuilder = new StringBuilder("invalid date range: [").append(date1).append(", ")
					.append(date2).append("]");

			throw new ResponseStatusException(HttpStatus.CONFLICT, strBuilder.toString());
		}

		double progress = ((double) elapsedDays / totalDays) * 100;
		logger.debug("progress: {}", progress);
		return progress;
	}

	@Override
	public List<IssueTeamMember> findIssueTeamById(String issueID) {
		// TODO Auto-generated method stub
		return issueTeamMemberRepository.findByParentId(issueID);
	}

	@Override
	public List<IssueTeamMemberBacklog> findIssueTeamBacklogById(String memberID, String parentID) {
		List<IssueTeamMemberBacklog> issueTeamMemberBacklog = new ArrayList<IssueTeamMemberBacklog>();
		issueTeamMemberBacklog = issueTeamMemberBacklogRepository.findByMemberId(memberID, parentID);
		for (IssueTeamMemberBacklog i : issueTeamMemberBacklog) {
			double progress = this.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetadaData("progress", Math.round(progress));
		}
		return issueTeamMemberBacklog;
	}

}
