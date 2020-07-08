package com.empathy.api.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.board.IBoardService;
import com.empathy.model.project.IssueTeamMemberBacklog;
import com.empathy.model.project.sprint.BacklogIssue;
import com.empathy.model.project.sprint.IssueTeamMember;
import com.empathy.model.project.sprint.Sprint;
import com.empathy.model.project.sprint.SprintId;

@RestController
@RequestMapping("/board")
public class BoardController {

	Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private IBoardService service;

	// sprint summary
	@GetMapping("/sprint/summary/{projectID}/{sprintID}")
	public Sprint getSprintSummary(@PathVariable String projectID, @PathVariable String sprintID,
			HttpServletRequest request) throws Exception {
		SprintId sprintId = new SprintId();
		sprintId.setProjectID(projectID);
		sprintId.setSprintID(sprintID);
		return service.findById(sprintId);
	}

	@GetMapping("/backlog/{sprintID}/{issueLevel}")
	public List<BacklogIssue> getBacklog(@PathVariable String sprintID,@PathVariable Integer issueLevel,
			HttpServletRequest request) throws Exception {
		
		return service.findBacklogBySprintIdAndIssueLevel(sprintID, issueLevel);
	}

	@GetMapping("/issue/team/{issueID}")
	public List<IssueTeamMember> getIssueTeam(@PathVariable String issueID, HttpServletRequest request)
			throws Exception {

		return service.findIssueTeamById(issueID);
	}
	
	@GetMapping("/issue/team/{issueID}")
	public List<IssueTeamMember> getIssueTeam(@PathVariable String sprintID,@PathVariable String issueID, HttpServletRequest request)
			throws Exception {

		return service.findSprintIssueTeamByIssueId(sprintID, issueID);
	}
	
	
	@GetMapping("/issue/team/member/{memberID}/backlog/{parentID}")
	public List<IssueTeamMemberBacklog> getIssueTeamMemberBacklog(@PathVariable String memberID, @PathVariable String parentID,
			HttpServletRequest request) throws Exception {
		logger.debug("/board/issue/team/member/{memberID}/backlog/{parentID}");
		return service.findIssueTeamBacklogById(memberID, parentID);
	}
	
	
	@GetMapping("/sprint/{sprintID}/issue/team/member/{memberID}/backlog/{parentID}")
	public List<IssueTeamMemberBacklog> findSprintIssueTeamMemberBacklog(@PathVariable String sprintID, @PathVariable String memberID, @PathVariable String parentID,
			HttpServletRequest request) throws Exception {
		logger.debug("/board/issue/team/member/{memberID}/backlog/{parentID}");
		return service.findSprintIssueTeamBacklogById(sprintID, memberID, parentID);
	}

}
