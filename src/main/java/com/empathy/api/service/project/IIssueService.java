package com.empathy.api.service.project;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.Issue;

@Repository
public interface IIssueService {

	Issue save(Issue issue) throws Exception;

	Issue findById(String issue);

	List<Issue> findAll();

	
	Boolean existsById(String issueID);

	void delete(Issue issue) throws Exception;
}
