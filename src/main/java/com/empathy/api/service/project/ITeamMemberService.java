package com.empathy.api.service.project;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.TeamMember;
import com.empathy.model.project.TeamMemberId;

@Repository
public interface ITeamMemberService {

	TeamMember save(TeamMember teamMember) throws Exception;

	TeamMember findById(TeamMemberId teamMemberId);

	List<TeamMember> findAll();

	void delete(TeamMember teamMember) throws Exception;

	Boolean existsById(TeamMemberId teamMemberId);
}
