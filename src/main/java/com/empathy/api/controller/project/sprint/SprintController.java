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

import com.empathy.api.service.project.sprint.ISprintService;
import com.empathy.model.project.sprint.Sprint;
import com.empathy.model.project.sprint.SprintId;

@RestController
@RequestMapping("/sprint")
public class SprintController {

	@Autowired
	private ISprintService service;

	// list
	@GetMapping("/a/find")
	public List<Sprint> getAll(HttpServletRequest request) throws Exception {
		return service.findAll();
	}

	// create
	@PostMapping("/a")
	public void post(@Valid @RequestBody Sprint sprint, HttpServletRequest request) throws Exception {
		
		service.save(sprint);
	}

	// read
	@GetMapping("/a/{sprintID}/{projectID}")
	public Sprint get(@PathVariable String sprintID, @PathVariable String projectID, HttpServletRequest request)
			throws Exception {

		SprintId id = new SprintId();
		id.setSprintID(sprintID);
		id.setProjectID(projectID);
		return service.findById(id);
	}

	// update
	@PutMapping("/a/{sprintID}/{projectID}")
	public void put(@PathVariable String sprintID, @PathVariable String projectID, @Valid @RequestBody Sprint sprint,
			HttpServletRequest request) throws Exception {
		SprintId sprintId = new SprintId();
		sprintId.setSprintID(sprintID);
		sprintId.setProjectID(projectID);
		sprint.setSprintID(sprintId);
		service.save(sprint);
	}

	// delete
	@DeleteMapping("/a/{sprintID}/{projectID}")
	public void deleteProject(@PathVariable String sprintID, @PathVariable String projectID,
			@Valid @RequestBody Sprint sprint, HttpServletRequest request) throws Exception {
		SprintId sprintId = new SprintId();
		sprintId.setSprintID(sprintID);
		sprintId.setProjectID(projectID);
		sprint.setSprintID(sprintId);
		service.delete(sprint);
	}
	
	
	@GetMapping("/u/{ownerID}/project/{projectID}")
	public List<Sprint> findByProjectId(@PathVariable String ownerID, @PathVariable String projectID ) {
		
		return service.findByProjectId(projectID);
	}
	
	
	


}
