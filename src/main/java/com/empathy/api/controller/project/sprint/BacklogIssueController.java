package com.empathy.api.controller.project.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.project.sprint.IBacklogIssueService;
import com.empathy.model.project.sprint.BacklogIssue;

@RestController
@RequestMapping("/backlog/issue")
public class BacklogIssueController {

	@Autowired
	private IBacklogIssueService service;

	@GetMapping("/{parentID}/childs/find")
	public List<BacklogIssue> findByChildsByParentID(@PathVariable String parentID) throws Exception {

		return service.findByChildsByParentID(parentID);
	}
	
	@GetMapping("/sprint/{sprintID}/root")
	public List<BacklogIssue> findRootBySprintID(@PathVariable String sprintID) throws Exception {

		return service.findRootBySprintID(sprintID);
	}

}
