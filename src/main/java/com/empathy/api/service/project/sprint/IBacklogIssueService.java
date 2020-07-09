package com.empathy.api.service.project.sprint;

import java.util.List;

import com.empathy.model.project.sprint.BacklogIssue;

public interface IBacklogIssueService {

	List<BacklogIssue> findByChildsByParentID(String parentID);

	List<BacklogIssue> findRootBySprintID(String sprintID);

}
