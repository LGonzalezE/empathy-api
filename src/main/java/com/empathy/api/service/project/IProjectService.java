package com.empathy.api.service.project;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.Project;

@Repository
public interface IProjectService {

	Project save(Project project) throws Exception;

	Project findById(String projectID);

	List<Project> findAll();

	
	Boolean existsById(String projectID);

	void delete(Project project) throws Exception;

	List<Project> findByOwnerId(String ownerID);
}
