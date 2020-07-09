package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.model.project.sprint.BacklogIssue;
import com.empathy.repository.project.sprint.BacklogIssueRepository;

@Service
public class BacklogIssueService implements IBacklogIssueService {
	@Autowired
	private BacklogIssueRepository repository;

	@Override
	public List<BacklogIssue> findByChildsByParentID(String parentID) {

		return repository.findByChildsByParentID(parentID);
	}

	@Override
	public List<BacklogIssue> findRootBySprintID(String sprintID) {
		return repository.findRootBySprintID(sprintID);
	}

}
