package com.empathy.api.service.project.sprint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.api.controller.board.BoardController;
import com.empathy.model.project.Issue;
import com.empathy.model.project.IssueLink;
import com.empathy.model.project.IssueLinkId;
import com.empathy.model.project.sprint.Backlog;
import com.empathy.model.project.sprint.BacklogId;
import com.empathy.model.project.sprint.IssueMemberDaily;
import com.empathy.model.project.sprint.IssueMemberDailyId;
import com.empathy.repository.project.IssueLinkRepository;
import com.empathy.repository.project.IssueRepository;
import com.empathy.repository.project.sprint.BacklogRepository;
import com.empathy.repository.project.sprint.IssueMemberDailyRepository;
import com.empathy.types.IssueLinkType;
import com.empathy.types.IssueType;

@Service
public class IssueMemberDailyService implements IIssueMemberDailyService {
	Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private IssueMemberDailyRepository repository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private IssueLinkRepository issueLinkRepository;

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Override
	public List<IssueMemberDaily> findAll() {
		return (List<IssueMemberDaily>) repository.findAll();

	}

	@Override
	public IssueMemberDaily save(IssueMemberDaily issueMemberDaily) throws Exception {

		switch (issueMemberDaily.getStatusID()) {
		case TODO:
			break;
		case DOING:
			break;
		case DONE:
			break;
		case BLOCKED:
			if (issueMemberDaily.getImpediments().size() == 0)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "impediment required");

			for(Issue issue : issueMemberDaily.getImpediments())
			{
				// create impediment
				issue.setIssueID(null);
				issue.setTypeID(IssueType.IMPEDIMENT);
				issueRepository.save(issue);
				// link impediment to issue
				IssueLink issueLink = new IssueLink();
				IssueLinkId issueLinkId = new IssueLinkId();
				issueLinkId.setParentID(issueMemberDaily.getIssueMemberDailyID().getIssueID());
				issueLinkId.setChildID(issue.getIssueID());
				issueLink.setIssueLinkID(issueLinkId);
				issueLink.setTypeID(IssueLinkType.ISBLOCKEDBY);
				issueLink.setCreatedBy(issueMemberDaily.getIssueMemberDailyID().getMemberID());
				issueLinkRepository.save(issueLink);
				
				//add issue to sprint backlog
				Backlog backlog= new Backlog();
				BacklogId backlogID = new BacklogId();				
				backlogID.setIssueID(issue.getIssueID());
				backlogID.setProjectID(issue.getProjectID());
				backlogID.setSprintID(issueMemberDaily.getIssueMemberDailyID().getSprintID());
				backlog.setBacklogID(backlogID);
				backlog.setIssueOrder(0);
				backlog.setCreatedBy(issueMemberDaily.getIssueMemberDailyID().getMemberID());
				backlogRepository.save(backlog);
			}
			
			break;
		case ABORTED:
			break;
		default:
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		}

		return repository.save(issueMemberDaily);
	}

	@Override
	public void delete(IssueMemberDaily issueMemberDaily) throws Exception {

		if (!repository.existsById(issueMemberDaily.getIssueMemberDailyID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		repository.deleteById(issueMemberDaily.getIssueMemberDailyID());
	}

	@Override
	public IssueMemberDaily findById(IssueMemberDailyId issueMemberDailyID) {

		if (!repository.existsById(issueMemberDailyID)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.findById(issueMemberDailyID).get();
	}

	@Override
	public Boolean existsById(IssueMemberDailyId issueMemberDailyID) {
		return repository.existsById(issueMemberDailyID);
	}

}
