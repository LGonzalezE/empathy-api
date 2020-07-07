package com.empathy.api.service.project;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.IssueLink;
import com.empathy.model.project.IssueLinkId;

@Repository
public interface IIssueLinkService {

	IssueLink save(IssueLink issueLink) throws Exception;

	IssueLink findById(IssueLinkId id);

	List<IssueLink> findAll();

	
	Boolean existsById(IssueLinkId id);

	void delete(IssueLink issueLink) throws Exception;
}
