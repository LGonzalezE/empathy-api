package com.empathy.api.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.model.project.TeamMemberProject;
import com.empathy.repository.project.TeamMemberProjectRepository;

@Service
public class TeamMemberProjectService implements ITeamMemberProjectService {
	@Autowired
	private TeamMemberProjectRepository repository;

	@Override
	public List<TeamMemberProject> findByMemberID(String memberID) {

		return repository.findByMemberID(memberID);
	}

	
}
