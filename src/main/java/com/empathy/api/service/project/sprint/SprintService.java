package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.model.project.sprint.Sprint;
import com.empathy.repository.project.sprint.SprintRepository;
import com.empathy.types.SprintStatus;
import com.empathy.util.IssueUtil;

@Service
public class SprintService implements ISprintService {
	@Autowired
	private SprintRepository repository;

	@Override
	public List<Sprint> findByProjectID(String projectID, SprintStatus statusID) throws Exception {
		List<Sprint> sprint = repository.findByProjectID(projectID, statusID);
		for (Sprint i : sprint) {
			double progress = IssueUtil.calculateProgress(i.getStartDate(), i.getEndDate());
			i.setMetaData("progress", Math.round(progress));
		}
		return sprint;
	}

}
