package com.empathy.api.controller.project.sprint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.project.sprint.ITeamMemberSprintIssueService;
import com.empathy.model.project.sprint.TeamMemberSprintIssue;

@RestController
@RequestMapping("/team/member/sprint")
public class TeamMemberSprintIssueController {

	@Autowired
	private ITeamMemberSprintIssueService service;

	// list
	@GetMapping("/u/{memberID}/{sprintID}/{issueLevel}")
	public List<TeamMemberSprintIssue> findBySprintIdAndMemberId(@PathVariable String memberID,
			@PathVariable String sprintID, @PathVariable Integer issueLevel, HttpServletRequest request)
			throws Exception {
		return service.findByMemberId(sprintID, memberID, issueLevel);
	}

	// list
	@GetMapping("/u/{memberID}/{sprintID}/{parentID}/{issueLevel}")
	public List<TeamMemberSprintIssue> findBySprintIdAndMemberId(@PathVariable String memberID,
			@PathVariable String sprintID, @PathVariable String parentID, @PathVariable Integer issueLevel,
			HttpServletRequest request) throws Exception {
		return service.findByMemberId(sprintID, memberID, parentID,issueLevel);
	}

}
