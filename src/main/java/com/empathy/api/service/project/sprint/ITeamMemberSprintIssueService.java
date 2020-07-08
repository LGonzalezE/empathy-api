package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.sprint.TeamMemberSprintIssue;

@Repository
public interface ITeamMemberSprintIssueService {

	List<TeamMemberSprintIssue> findByMemberId(String memberID, String sprintID, Integer issueLevel) throws Exception;

	List<TeamMemberSprintIssue> findByMemberId(String sprintID, String memberID, String parentID, Integer issueLevel) throws Exception;

}
