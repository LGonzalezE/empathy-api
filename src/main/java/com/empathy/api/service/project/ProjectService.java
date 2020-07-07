package com.empathy.api.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.model.project.Project;
import com.empathy.repository.project.ProjectRepository;

@Service
public class ProjectService implements IProjectService {
	@Autowired
	private ProjectRepository repository;

	@Override
	public List<Project> findAll() {
		return (List<Project>) repository.findAll();

	}

	@Override
	public Project save(Project project) throws Exception {

		return repository.save(project);
	}
	
	@Override
	public void delete(Project project) throws Exception {

		repository.deleteById(project.getProjectID());
	}


	@Override
	public Project findById(String projectID) {
		return repository.findById(projectID).get();
	}

	@Override
	public Boolean existsById(String projectID) {

		return repository.existsById(projectID);
	}

	@Override
	public List<Project> findByOwnerId(String ownerID) {
		
		return repository.findByOwnerId(ownerID);
	}

	
}
