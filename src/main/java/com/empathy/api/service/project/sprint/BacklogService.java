package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.project.sprint.Backlog;
import com.empathy.model.project.sprint.BacklogId;
import com.empathy.repository.project.sprint.BacklogRepository;

@Service
public class BacklogService implements IBacklogService {
	@Autowired
	private BacklogRepository repository;

	@Override
	public List<Backlog> findAll() {
		return (List<Backlog>) repository.findAll();

	}

	@Override
	public Backlog save(Backlog backlog) throws Exception {

		return repository.save(backlog);
	}

	@Override
	public void delete(Backlog backlog) throws Exception {

		if (!repository.existsById(backlog.getBacklogID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		repository.deleteById(backlog.getBacklogID());
	}

	@Override
	public Backlog findById(BacklogId backlogID) {

		if (!repository.existsById(backlogID)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.findById(backlogID).get();
	}

	@Override
	public Boolean existsById(BacklogId backlogID) {
		return repository.existsById(backlogID);
	}

}
