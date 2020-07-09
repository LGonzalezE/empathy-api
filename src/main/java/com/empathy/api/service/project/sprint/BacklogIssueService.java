package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.model.project.sprint.BacklogIssue;
import com.empathy.repository.project.sprint.BacklogIssueRepository;
import com.empathy.util.IssueUtil;

@Service
public class BacklogIssueService implements IBacklogIssueService {
	@Autowired
	private BacklogIssueRepository repository;

	@Override
	public List<BacklogIssue> findByChildsByParentID(String parentID) throws Exception {

		List<BacklogIssue>backlogIssue = repository.findByChildsByParentID(parentID);
		for (BacklogIssue i : backlogIssue) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetaData("progress", Math.round(progress));
		}
		
		return backlogIssue;
	}

	@Override
	public List<BacklogIssue> findRootBySprintID(String sprintID) throws Exception {
		
		List<BacklogIssue>backlogIssue =repository.findRootBySprintID(sprintID);
		for (BacklogIssue i : backlogIssue) {
			double progress = IssueUtil.calculateProgress(i.getCreatedDate(), i.getEstimatedDate());
			i.setMetaData("progress", Math.round(progress));
		}
		
		return backlogIssue;
	}

}
