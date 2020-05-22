package com.empathy.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.projects.Project;
import com.empathy.model.projects.VProjectTeam;

@Repository
public interface IProjectService {

	Project save(Project project) throws Exception;
	
	Project findById(String projectID);

	List<Project> findAll();

	VProjectTeam findByMemberId(String projectID, String ownerID);

	List<VProjectTeam> findByMemberId(String memberID);
}
