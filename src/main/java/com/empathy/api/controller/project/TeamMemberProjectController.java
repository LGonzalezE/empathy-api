package com.empathy.api.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.project.ITeamMemberProjectService;
import com.empathy.model.project.TeamMemberProject;

@RestController
@RequestMapping("/project/team/member")
public class TeamMemberProjectController {

	@Autowired
	private ITeamMemberProjectService service;

	// read
	@GetMapping("/{memberID}/project/find")
	public List<TeamMemberProject> findTeamMemberProject(@PathVariable String memberID, HttpServletRequest request) throws Exception {

		return service.findByMemberID(memberID);
	}

	

}
