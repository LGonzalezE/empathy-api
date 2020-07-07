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

import com.empathy.api.service.project.sprint.IIssueMemberDailyService;
import com.empathy.model.project.sprint.IssueMemberDaily;
import com.empathy.model.project.sprint.IssueMemberDailyId;

@RestController
@RequestMapping("/daily")
public class IssueMemberDailyController {

	@Autowired
	private IIssueMemberDailyService service;

	// list
	@GetMapping("/a/find")
	public List<IssueMemberDaily> getAll(HttpServletRequest request) throws Exception {
		return service.findAll();
	}

	// create
	@PostMapping("/a")
	public void post(@Valid @RequestBody IssueMemberDaily issueMemberDaily, HttpServletRequest request)
			throws Exception {
		service.save(issueMemberDaily);
	}

	// read
	@GetMapping("/a/{sprintID}/{projectID}/{issueID}/{memberID}")
	public IssueMemberDaily get(@PathVariable String sprintID, @PathVariable String projectID,
			@PathVariable String issueID, @PathVariable String memberID, HttpServletRequest request) throws Exception {

		IssueMemberDailyId issueMemberDailyID = new IssueMemberDailyId();
		issueMemberDailyID.setSprintID(sprintID);
		issueMemberDailyID.setProjectID(projectID);
		issueMemberDailyID.setIssueID(issueID);
		issueMemberDailyID.setMemberID(memberID);
		return service.findById(issueMemberDailyID);
	}

	// update
	@PutMapping("/a/{sprintID}/{projectID}/{issueID}/{memberID}")
	public void put(@PathVariable String sprintID, @PathVariable String projectID, @PathVariable String issueID,
			@PathVariable String memberID, @Valid @RequestBody IssueMemberDaily issueMemberDaily,
			HttpServletRequest request) throws Exception {
		IssueMemberDailyId issueMemberDailyID = new IssueMemberDailyId();
		issueMemberDailyID.setSprintID(sprintID);
		issueMemberDailyID.setProjectID(projectID);
		issueMemberDailyID.setIssueID(issueID);
		issueMemberDailyID.setMemberID(memberID);
		issueMemberDaily.setIssueMemberDailyID(issueMemberDailyID);
		service.save(issueMemberDaily);
	}

	// delete
	@DeleteMapping("/a/{sprintID}/{projectID}/{issueID}/{memberID}")
	public void deleteProject(@PathVariable String sprintID, @PathVariable String projectID,
			@PathVariable String issueID, @PathVariable String memberID,
			@Valid @RequestBody IssueMemberDaily issueMemberDaily, HttpServletRequest request) throws Exception {
		IssueMemberDailyId issueMemberDailyID = new IssueMemberDailyId();
		issueMemberDailyID.setSprintID(sprintID);
		issueMemberDailyID.setProjectID(projectID);
		issueMemberDailyID.setIssueID(issueID);
		issueMemberDailyID.setMemberID(memberID);
		issueMemberDaily.setIssueMemberDailyID(issueMemberDailyID);
		service.delete(issueMemberDaily);
	}

	// create
	@PostMapping("/u")
	public void postIssueMemberDaily(@Valid @RequestBody IssueMemberDaily issueMemberDaily, HttpServletRequest request)
			throws Exception {
		service.save(issueMemberDaily);
	}

}
