package com.empathy.api.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.api.service.project.IProjectService;
import com.empathy.model.project.Project;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private IProjectService service;

	// create
	@PostMapping("/a")
	public void postProject(@Valid @RequestBody Project project, HttpServletRequest request) throws Exception {
		service.save(project);
	}

	// read
	@GetMapping("/a/{projectID}")
	public Project getProject(@PathVariable String projectID, HttpServletRequest request) throws Exception {

		if (!service.existsById(projectID)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return service.findById(projectID);
	}

	// update
	@PutMapping("/a/{projectID}")
	public void putProject(@PathVariable String projectID, @Valid @RequestBody Project project,
			HttpServletRequest request) throws Exception {
		project.setProjectID(projectID);
		service.save(project);
	}

	// delete
	@DeleteMapping("/a/{projectID}")
	public void deleteProject(@PathVariable String projectID, HttpServletRequest request) throws Exception {
		Project project = new Project();
		project.setProjectID(projectID);
		service.delete(project);
	}

	// list
	@GetMapping("/a/find")
	public List<Project> getProject(HttpServletRequest request) throws Exception {
		return service.findAll();
	}

	//users services
	// list
	@GetMapping("/u/{ownerID}/find")
	public List<Project> findByOwnerId(@PathVariable String ownerID, HttpServletRequest request) throws Exception {
		return service.findByOwnerId(ownerID);
	}

}
