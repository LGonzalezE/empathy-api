package com.empathy.api.controller.project.sprint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.project.sprint.IBacklogService;
import com.empathy.model.project.sprint.Backlog;
import com.empathy.model.project.sprint.BacklogId;

@RestController
@RequestMapping("/backlog")
public class BacklogController {

	@Autowired
	private IBacklogService service;

	// list
	@GetMapping("/a/find")
	public List<Backlog> getAll(HttpServletRequest request) throws Exception {
		return service.findAll();
	}

	// create
	@PostMapping("/a")
	public void post(@Valid @RequestBody Backlog backlog, HttpServletRequest request) throws Exception {
		service.save(backlog);
	}

	// read
	@GetMapping("/a/{sprintID}/{projectID}/{issueID}")
	public Backlog get(@PathVariable String sprintID, @PathVariable String projectID, @PathVariable String issueID,
			HttpServletRequest request) throws Exception {

		BacklogId backlogID = new BacklogId();
		backlogID.setSprintID(sprintID);
		backlogID.setProjectID(projectID);
		backlogID.setIssueID(issueID);
		return service.findById(backlogID);
	}

	// update
	@PutMapping("/a/{sprintID}/{projectID}/{issueID}")
	public void put(@PathVariable String sprintID, @PathVariable String projectID, @PathVariable String issueID,
			@Valid @RequestBody Backlog backlog, HttpServletRequest request) throws Exception {
		BacklogId backlogID = new BacklogId();
		backlogID.setSprintID(sprintID);
		backlogID.setProjectID(projectID);
		backlogID.setIssueID(issueID);
		backlog.setBacklogID(backlogID);
		service.save(backlog);
	}

	// delete
	@DeleteMapping("/a/{sprintID}/{projectID}/{issueID}")
	public void deleteProject(@PathVariable String sprintID, @PathVariable String projectID,@PathVariable String issueID,
			@Valid @RequestBody Backlog backlog, HttpServletRequest request) throws Exception {
		BacklogId backlogID = new BacklogId();
		backlogID.setSprintID(sprintID);
		backlogID.setProjectID(projectID);
		backlogID.setIssueID(issueID);
		backlog.setBacklogID(backlogID);
		service.delete(backlog);
	}

}
