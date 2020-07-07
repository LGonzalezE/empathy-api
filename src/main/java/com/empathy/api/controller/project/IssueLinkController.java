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

import com.empathy.api.service.project.IIssueLinkService;
import com.empathy.model.project.IssueLink;
import com.empathy.model.project.IssueLinkId;

@RestController
@RequestMapping("/issueLink")
public class IssueLinkController {

	@Autowired
	private IIssueLinkService service;

	// create
	@PostMapping("/a")
	public void post(@Valid @RequestBody IssueLink issueLink, HttpServletRequest request) throws Exception {
		service.save(issueLink);
	}

	// read
	@GetMapping("/a/{parentID}/{childID}")
	public IssueLink get(@PathVariable String parentID, @PathVariable String childID, HttpServletRequest request)
			throws Exception {

		IssueLinkId issueLinkID = new IssueLinkId();
		issueLinkID.setParentID(parentID);
		issueLinkID.setChildID(childID);
		return service.findById(issueLinkID);
	}

	// update
	@PutMapping("/a/{parentID}/{childID}")
	public void put(@PathVariable String parentID, @PathVariable String childID, @Valid @RequestBody IssueLink issue,
			HttpServletRequest request) throws Exception {
		IssueLinkId issueLinkID = new IssueLinkId();
		issueLinkID.setParentID(parentID);
		issueLinkID.setChildID(childID);
		issue.setIssueLinkID(issueLinkID);
		service.save(issue);
	}

	// delete
	@DeleteMapping("/a/{parentID}/{childID}")
	public void deleteProject(@PathVariable String parentID, @PathVariable String childID,
			@Valid @RequestBody IssueLink issue, HttpServletRequest request) throws Exception {
		IssueLinkId issueLinkID = new IssueLinkId();
		issueLinkID.setParentID(parentID);
		issueLinkID.setChildID(childID);
		issue.setIssueLinkID(issueLinkID);
		service.delete(issue);
	}

	// list
	@GetMapping("/a/find")
	public List<IssueLink> getAll(HttpServletRequest request) throws Exception {
		return service.findAll();
	}

}
