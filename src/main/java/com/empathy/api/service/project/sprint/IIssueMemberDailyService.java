package com.empathy.api.service.project.sprint;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.project.sprint.IssueMemberDaily;
import com.empathy.model.project.sprint.IssueMemberDailyId;

@Repository
public interface IIssueMemberDailyService {

	IssueMemberDaily save(IssueMemberDaily issueMemberDaily) throws Exception;

	IssueMemberDaily findById(IssueMemberDailyId issueMemberDailyId);

	List<IssueMemberDaily> findAll();

	void delete(IssueMemberDaily issueMemberDaily) throws Exception;

	Boolean existsById(IssueMemberDailyId issueMemberDailyID);
}
