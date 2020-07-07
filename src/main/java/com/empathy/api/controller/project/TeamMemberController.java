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

import com.empathy.api.service.project.ITeamMemberService;
import com.empathy.model.project.TeamMember;
import com.empathy.model.project.TeamMemberId;

@RestController
@RequestMapping("/teamMember")
public class TeamMemberController {

	@Autowired
	private ITeamMemberService service;

	// create
	@PostMapping("/a")
	public void post(@Valid @RequestBody TeamMember teamMember, HttpServletRequest request) throws Exception {
		service.save(teamMember);
	}

	// read
	@GetMapping("/a/{projectID}/{userID}")
	public TeamMember get(@PathVariable String projectID, @PathVariable String userID, HttpServletRequest request)
			throws Exception {

		TeamMemberId teamMemberID = new TeamMemberId();
		teamMemberID.setProjectID(projectID);
		teamMemberID.setUserID(userID);
		return service.findById(teamMemberID);
	}

	// update
	@PutMapping("/a/{projectID}/{userID}")
	public void put(@PathVariable String projectID, @PathVariable String userID,
			@Valid @RequestBody TeamMember teamMember, HttpServletRequest request) throws Exception {
		TeamMemberId teamMemberID = new TeamMemberId();
		teamMemberID.setProjectID(projectID);
		teamMemberID.setUserID(userID);
		teamMember.setIssueLinkID(teamMemberID);
		service.save(teamMember);
	}

	// delete
	@DeleteMapping("/a/{projectID}/{userID}")
	public void deleteProject(@PathVariable String projectID, @PathVariable String userID,
			@Valid @RequestBody TeamMember teamMember, HttpServletRequest request) throws Exception {
		TeamMemberId teamMemberID = new TeamMemberId();
		teamMemberID.setProjectID(projectID);
		teamMemberID.setUserID(userID);
		teamMember.setIssueLinkID(teamMemberID);
		service.delete(teamMember);
	}

	// list
	@GetMapping("/a/find")
	public List<TeamMember> getAll(HttpServletRequest request) throws Exception {
		return service.findAll();
	}

}
