package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.sprint.Backlog;
import com.empathy.model.project.sprint.BacklogId;

@Repository
public interface IBacklogService {

	Backlog save(Backlog backlog) throws Exception;

	Backlog findById(BacklogId backlogID);

	List<Backlog> findAll();

	void delete(Backlog backlog) throws Exception;

	Boolean existsById(BacklogId backlogID);
}
