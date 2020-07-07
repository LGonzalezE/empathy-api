package com.empathy.api.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.project.Issue;
import com.empathy.repository.project.IssueRepository;

@Service
public class IssueService implements IIssueService {
	@Autowired
	private IssueRepository repository;

	@Override
	public List<Issue> findAll() {
		return (List<Issue>) repository.findAll();

	}

	@Override
	public Issue save(Issue issue) throws Exception {

		if (issue.getIssueID() != null && !repository.existsById(issue.getIssueID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.save(issue);
	}

	@Override
	public void delete(Issue issue) throws Exception {

		if (!repository.existsById(issue.getIssueID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		repository.deleteById(issue.getIssueID());
	}

	@Override
	public Issue findById(String issue) {
		
		if (!repository.existsById(issue)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return repository.findById(issue).get();
	}

	@Override
	public Boolean existsById(String issueID) {
		return repository.existsById(issueID);
	}

}
