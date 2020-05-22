package com.empathy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.model.projects.Project;
import com.empathy.model.projects.VProjectTeam;
import com.empathy.service.IProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private IProjectService projectService;

	@GetMapping("/{projectID}")
	public Project getProject(@PathVariable String projectID, HttpServletRequest request) throws Exception {

		return projectService.findById(projectID);
	}

	@GetMapping("/{userID}/{projectID}")
	public VProjectTeam getOwnerProject(@PathVariable String userID, @PathVariable String projectID,
			HttpServletRequest request) throws Exception {
		return projectService.findByMemberId(projectID, userID);

	}

	@GetMapping("/{userID}/browse")
	public List<VProjectTeam> getOwnerProject(@PathVariable String userID, HttpServletRequest request) throws Exception {

		return projectService.findByMemberId(userID);

	}
	
	@PutMapping("/{userID}/")
	public Project putOwnerProject(@PathVariable String userID, @Valid @RequestBody Project project,
			HttpServletRequest request) throws Exception {
		return projectService.save(project);

	}
	
	
}
