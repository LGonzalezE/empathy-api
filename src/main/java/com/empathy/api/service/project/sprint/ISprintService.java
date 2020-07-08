package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.sprint.Sprint;
import com.empathy.types.SprintStatus;

@Repository
public interface ISprintService {

	List<Sprint> findByProjectID(String projectID, SprintStatus statusID);
}
