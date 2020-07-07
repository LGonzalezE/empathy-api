package com.empathy.api.controller.project;

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

import com.empathy.api.service.project.IIssueService;
import com.empathy.model.project.Issue;

@RestController
@RequestMapping("/issue")
public class IssueController {

	@Autowired
	private IIssueService service;

	// create
	@PostMapping("/a")
	public void post(@Valid @RequestBody Issue issue, HttpServletRequest request) throws Exception {
		issue.setIssueID(null);
		service.save(issue);
	}

	// read
	@GetMapping("/a/{issueID}")
	public Issue get(@PathVariable String issueID, HttpServletRequest request) throws Exception {

		return service.findById(issueID);
	}

	// update
	@PutMapping("/a/{issueID}")
	public void put(@PathVariable String issueID, @Valid @RequestBody Issue issue, HttpServletRequest request)
			throws Exception {

		issue.setIssueID(issueID);
		service.save(issue);
	}

	// delete
	@DeleteMapping("/a/{issueID}")
	public void deleteProject(@PathVariable String issueID, HttpServletRequest request) throws Exception {
		Issue issue = new Issue();
		issue.setIssueID(issueID);
		service.delete(issue);
	}

	// list
	@GetMapping("/a/find")
	public List<Issue> getAll(HttpServletRequest request) throws Exception {
		return service.findAll();
	}
}
