package com.empathy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.model.projects.Project;
import com.empathy.model.projects.VProjectTeam;
import com.empathy.repository.projects.ProjectRepository;

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
	public VProjectTeam findByMemberId(String projectID, String memberID) {
		return repository.findByMemberId(projectID, memberID);
	}

	@Override
	public List<VProjectTeam> findByMemberId(String memberID) {
		return repository.findByMemberId(memberID);
	}

	@Override
	public Project findById(String projectID) {
		return repository.findById(projectID).get();
	}
}
