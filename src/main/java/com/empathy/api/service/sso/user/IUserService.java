package com.empathy.api.service.sso.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.empathy.model.sso.user.User;

@Repository
public interface IUserService {

	User save(User user) throws Exception;

	User findById(String userID);

	List<User> findAll();

	void delete(User user) throws Exception;

	Boolean existsById(String userID);
}
