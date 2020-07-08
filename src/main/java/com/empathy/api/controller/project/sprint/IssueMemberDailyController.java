package com.empathy.api.controller.project.sprint;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.project.sprint.IIssueMemberDailyService;
import com.empathy.model.project.sprint.IssueMemberDaily;

@RestController
@RequestMapping("/daily")
public class IssueMemberDailyController {

	@Autowired
	private IIssueMemberDailyService service;

	
	// create
	@PostMapping("/u")
	public void postIssueMemberDaily(@Valid @RequestBody IssueMemberDaily issueMemberDaily, HttpServletRequest request)
			throws Exception {
		service.save(issueMemberDaily);
	}

}
