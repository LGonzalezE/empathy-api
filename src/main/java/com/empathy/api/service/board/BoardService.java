package com.empathy.api.service.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.project.IssueTeamMemberBacklog;
import com.empathy.model.project.sprint.BacklogIssue;
import com.empathy.model.project.sprint.IssueTeamMember;
import com.empathy.model.project.sprint.Sprint;
import com.empathy.model.project.sprint.SprintId;
import com.empathy.repository.project.sprint.BacklogIssueRepository;
import com.empathy.repository.project.sprint.IssueTeamMemberBacklogRepository;
import com.empathy.repository.project.sprint.IssueTeamMemberRepository;
import com.empathy.repository.project.sprint.SprintRepository;
import com.empathy.util.IssueUtil;

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
	public Sprint findById(SprintId sprintID) throws Exception {

		Optional<Sprint> oSprint = sprintRepository.findById(sprintID);

		if (!oSprint.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Sprint sprint = oSprint.get();
	
		double progress = IssueUtil.calculateProgress(sprint.getStartDate(), sprint.getEndDate());
		sprint.getMetaData().put("progress", progress);
		return sprint;
	}

	@Override
	public List<BacklogIssue> findBacklogBySprintIdAndIssueLevel(String sprintID, Integer issueLevel) throws Exception {

		List<BacklogIssue> backlogIssue = new ArrayList<BacklogIssue>();
		backlogIssue = backlogIssueRepository.findBySprintIdAndIssueLevel(sprintID, issueLevel);

		for (BacklogIssue i : backlogIssue) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetadaData("progress", Math.round(progress));
		}
		return backlogIssue;
	}

	

	@Override
	public List<IssueTeamMember> findIssueTeamById(String issueID) {
		// TODO Auto-generated method stub
		return issueTeamMemberRepository.findByParentId(issueID);
	}

	@Override
	public List<IssueTeamMemberBacklog> findIssueTeamBacklogById(String memberID, String parentID) throws Exception {
		List<IssueTeamMemberBacklog> issueTeamMemberBacklog = new ArrayList<IssueTeamMemberBacklog>();
		issueTeamMemberBacklog = issueTeamMemberBacklogRepository.findByMemberId(memberID, parentID);
		for (IssueTeamMemberBacklog i : issueTeamMemberBacklog) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetaData("progress", Math.round(progress));
		}
		return issueTeamMemberBacklog;
	}

	@Override
	public List<BacklogIssue> findBacklogBySprintId(String sprintID) throws Exception {
		List<BacklogIssue> backlogIssue = new ArrayList<BacklogIssue>();
		backlogIssue = backlogIssueRepository.findBySprintId(sprintID);

		for (BacklogIssue i : backlogIssue) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetadaData("progress", Math.round(progress));
		}
		return backlogIssue;
	}

	@Override
	public List<IssueTeamMemberBacklog> findSprintIssueTeamBacklogById(String sprintID, String memberID,
			String parentID) throws Exception {
		List<IssueTeamMemberBacklog> issueTeamMemberBacklog = new ArrayList<IssueTeamMemberBacklog>();
		issueTeamMemberBacklog = issueTeamMemberBacklogRepository.findByStringIdAndMemberId(memberID, parentID);
		for (IssueTeamMemberBacklog i : issueTeamMemberBacklog) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetaData("progress", Math.round(progress));
		}
		return issueTeamMemberBacklog;
	}

	@Override
	public List<IssueTeamMember> findSprintIssueTeamByIssueId(String sprintID, String issueID) {

		return issueTeamMemberRepository.findByParentId(issueID);
	}

}
