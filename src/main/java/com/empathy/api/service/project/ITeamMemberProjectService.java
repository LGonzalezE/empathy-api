package com.empathy.api.service.project;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.TeamMemberProject;

@Repository
public interface ITeamMemberProjectService {

	List<TeamMemberProject> findByMemberID(String memberID);


}
