package com.empathy.api.service.project.sprint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.model.project.sprint.TeamMemberSprintIssue;
import com.empathy.repository.project.sprint.TeamMemberSprintIssueRepository;
import com.empathy.util.IssueUtil;

@Service
public class TeamMemberSprintIssueService implements ITeamMemberSprintIssueService {
	@Autowired
	private TeamMemberSprintIssueRepository repository;

	@Override
	public List<TeamMemberSprintIssue> findByMemberId(String sprintID, String memberID, Integer issueLevel)
			throws Exception {

		List<TeamMemberSprintIssue> teamMemberSprintIssue = new ArrayList<TeamMemberSprintIssue>();
		teamMemberSprintIssue = repository.findByMemberId(memberID, sprintID, issueLevel);

		for (TeamMemberSprintIssue i : teamMemberSprintIssue) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.getMetaData().put("progress", Math.round(progress));
		}
		return teamMemberSprintIssue;

	}

	@Override
	public List<TeamMemberSprintIssue> findByMemberId(String sprintID, String memberID, String parentID,
			Integer issueLevel) throws Exception {
		List<TeamMemberSprintIssue> teamMemberSprintIssue = new ArrayList<TeamMemberSprintIssue>();
		teamMemberSprintIssue = repository.findByMemberId(memberID, sprintID,parentID, issueLevel);

		for (TeamMemberSprintIssue i : teamMemberSprintIssue) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.getMetaData().put("progress", Math.round(progress));
		}
		return teamMemberSprintIssue;

	}

}
