package com.empathy.api.service.board;

import java.util.List;

import com.empathy.api.dto.board.SprintSummary;
import com.empathy.model.project.sprint.BacklogIssue;
import com.empathy.model.project.sprint.IssueTeamMember;
import com.empathy.model.project.sprint.IssueTeamMemberBacklog;
import com.empathy.model.project.sprint.SprintId;

public interface IBoardService {

	SprintSummary findById(SprintId sprintID) throws Exception;

	List<IssueTeamMember> findIssueTeamById(String issueID);

	List<IssueTeamMemberBacklog> findIssueTeamBacklogById(String memberID, String parentID);

	List<BacklogIssue> findBacklogBySprintId(String sprintID);

}
