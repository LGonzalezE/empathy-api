package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.sprint.Sprint;
import com.empathy.model.project.sprint.SprintId;

@Repository
public interface ISprintService {

	Sprint save(Sprint teamMember) throws Exception;

	Sprint findById(SprintId teamMemberId);

	List<Sprint> findAll();

	void delete(Sprint teamMember) throws Exception;

	Boolean existsById(SprintId teamMemberId);

	List<Sprint> findByProjectId(String projectID);
}
