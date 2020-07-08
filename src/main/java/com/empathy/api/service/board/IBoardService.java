package com.empathy.api.service.board;

import java.util.List;

import com.empathy.model.project.IssueTeamMemberBacklog;
import com.empathy.model.project.sprint.BacklogIssue;
import com.empathy.model.project.sprint.IssueTeamMember;
import com.empathy.model.project.sprint.Sprint;
import com.empathy.model.project.sprint.SprintId;

public interface IBoardService {

	Sprint findById(SprintId sprintID) throws Exception;

	List<IssueTeamMember> findIssueTeamById(String issueID);
	
	
	
	List<IssueTeamMemberBacklog> findIssueTeamBacklogById(String memberID, String parentID) throws Exception;

	List<BacklogIssue> findBacklogBySprintId(String sprintID) throws Exception;
	
	List<BacklogIssue> findBacklogBySprintIdAndIssueLevel(String issueID, Integer issueLevel) throws Exception;

	List<IssueTeamMemberBacklog> findSprintIssueTeamBacklogById(String sprintID, String memberID, String parentID) throws Exception;

	List<IssueTeamMember> findSprintIssueTeamByIssueId(String sprintID, String issueID);

}
