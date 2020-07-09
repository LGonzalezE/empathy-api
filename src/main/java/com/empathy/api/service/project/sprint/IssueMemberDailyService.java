package com.empathy.api.service.project.sprint;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.project.Issue;
import com.empathy.model.project.sprint.Backlog;
import com.empathy.model.project.sprint.BacklogId;
import com.empathy.model.project.sprint.IssueMemberDaily;
import com.empathy.model.project.sprint.IssueMemberDailyId;
import com.empathy.repository.project.IssueRepository;
import com.empathy.repository.project.sprint.BacklogRepository;
import com.empathy.repository.project.sprint.IssueMemberDailyRepository;
import com.empathy.types.IssueStatus;
import com.empathy.types.IssueType;

@Service
public class IssueMemberDailyService implements IIssueMemberDailyService {
	Logger logger = LoggerFactory.getLogger(IssueMemberDailyService.class);

	@Autowired
	private IssueMemberDailyRepository repository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	@Override
	public List<IssueMemberDaily> findAll() {
		return (List<IssueMemberDaily>) repository.findAll();

	}

	@Override
	public IssueMemberDaily save(IssueMemberDaily issueMemberDaily) throws Exception {

		issueMemberDaily.getIssueMemberDailyID().setDailyDate(new Date());

		Issue currentIssue = issueRepository.findById(issueMemberDaily.getIssueMemberDailyID().getIssueID()).get();

		// El usuario no debe registrar avances sobre incidentes cerrados
		if (currentIssue.getStatusID() == IssueStatus.DONE) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "can't update issue when is DONE");
		}

		// El usuario no puede registrar impedimentos de incidentes dónde no es el
		// responsable
		logger.debug("ownerID: {}, memberID: {}", currentIssue.getOwnerID(),
				issueMemberDaily.getIssueMemberDailyID().getMemberID());
		if (!currentIssue.getOwnerID().equals(issueMemberDaily.getIssueMemberDailyID().getMemberID())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "can only update the owner");
		}

		if (issueMemberDaily.getStatusID() == IssueStatus.DONE) {
			List<Issue> childs = issueRepository.findChildByIssueID(currentIssue.getIssueID());

			// Un incidente no puede ser cerrado si uno o más de los sub-incidentes están
			// abiertos
			for (Issue child : childs) {
				switch (child.getStatusID()) {
				case DONE:
					break;
				case ABORTED:
					break;
				default:
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "there are issues opened");
				}
			}
		}

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

			// El usuario puede registrar diariamente impedimentos para avanzar en los
			// incidentes

			for (Issue impediment : issueMemberDaily.getImpediments()) {
				// create impediment
				impediment.setIssueID(null);
				impediment.setParentID(null);
				impediment.setProjectID(currentIssue.getProjectID());
				impediment.setTypeID(IssueType.IMPEDIMENT);
				impediment.setStatusID(IssueStatus.TODO);
				impediment.setEstimatedDate(new Date());
				impediment.setOwnerID(currentIssue.getOwnerID());
				issueRepository.save(impediment);

				// add issue to sprint backlog
				Backlog backlog = new Backlog();
				BacklogId backlogID = new BacklogId();
				backlogID.setIssueID(impediment.getIssueID());
				backlogID.setProjectID(impediment.getProjectID());
				backlogID.setSprintID(issueMemberDaily.getIssueMemberDailyID().getSprintID());
				backlog.setBacklogID(backlogID);
				backlog.setIssueOrder(0);
				backlog.setCreatedBy(currentIssue.getOwnerID());
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

