package com.empathy.api.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.project.TeamMember;
import com.empathy.model.project.TeamMemberId;
import com.empathy.repository.project.TeamMemberRepository;

@Service
public class TeamMemberService implements ITeamMemberService {
	@Autowired
	private TeamMemberRepository repository;

	@Override
	public List<TeamMember> findAll() {
		return (List<TeamMember>) repository.findAll();

	}

	@Override
	public TeamMember save(TeamMember teamMember) throws Exception {

		return repository.save(teamMember);
	}

	@Override
	public void delete(TeamMember teamMember) throws Exception {

		if (!repository.existsById(teamMember.getTeamMemberID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		repository.deleteById(teamMember.getTeamMemberID());
	}

	@Override
	public TeamMember findById(TeamMemberId teamMemberId) {

		if (!repository.existsById(teamMemberId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.findById(teamMemberId).get();
	}

	@Override
	public Boolean existsById(TeamMemberId teamMemberId) {
		return repository.existsById(teamMemberId);
	}

}
