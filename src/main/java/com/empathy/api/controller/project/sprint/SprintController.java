package com.empathy.api.controller.project.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.project.sprint.ISprintService;
import com.empathy.model.project.sprint.Sprint;
import com.empathy.types.SprintStatus;

@RestController
@RequestMapping("/sprint")
public class SprintController {

	@Autowired
	private ISprintService service;

	@GetMapping("/project/{projectID}/status/{statusID}/find")
	public List<Sprint> findByProjectID(@PathVariable String projectID, @PathVariable SprintStatus statusID) throws Exception {

		return service.findByProjectID(projectID, statusID);
	}

}
