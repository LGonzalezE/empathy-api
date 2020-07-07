package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.project.sprint.Sprint;
import com.empathy.model.project.sprint.SprintId;
import com.empathy.repository.project.sprint.SprintRepository;
import com.empathy.util.UUIDGenerator;

@Service
public class SprintService implements ISprintService {
	@Autowired
	private SprintRepository repository;

	@Override
	public List<Sprint> findAll() {
		return (List<Sprint>) repository.findAll();

	}

	@Override
	public Sprint save(Sprint sprint) throws Exception {
		if (sprint.getSprintID().getSprintID() == null) {
			String sprintID = UUIDGenerator.generateType4UUID().toString();
			sprint.getSprintID().setSprintID(sprintID);
		}

		return repository.save(sprint);
	}

	@Override
	public void delete(Sprint sprint) throws Exception {

		if (!repository.existsById(sprint.getSprintID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		repository.deleteById(sprint.getSprintID());
	}

	@Override
	public Sprint findById(SprintId sprintID) {

		if (!repository.existsById(sprintID)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.findById(sprintID).get();
	}

	@Override
	public Boolean existsById(SprintId sprintID) {
		return repository.existsById(sprintID);
	}

	@Override
	public List<Sprint> findByProjectId(String projectID) {

		return repository.findByProjectId(projectID);
	}

}
