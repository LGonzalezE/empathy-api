package com.empathy.api.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.project.IssueLink;
import com.empathy.model.project.IssueLinkId;
import com.empathy.repository.project.IssueLinkRepository;

@Service
public class IssueLinkService implements IIssueLinkService {
	@Autowired
	private IssueLinkRepository repository;

	@Override
	public List<IssueLink> findAll() {
		return (List<IssueLink>) repository.findAll();

	}

	@Override
	public IssueLink save(IssueLink issueLink) throws Exception {

		return repository.save(issueLink);
	}

	@Override
	public void delete(IssueLink issueLink) throws Exception {

		if (!repository.existsById(issueLink.getIssueLinkID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		repository.deleteById(issueLink.getIssueLinkID());
	}

	@Override
	public IssueLink findById(IssueLinkId id) {

		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.findById(id).get();
	}

	@Override
	public Boolean existsById(IssueLinkId id) {
		return repository.existsById(id);
	}

}
