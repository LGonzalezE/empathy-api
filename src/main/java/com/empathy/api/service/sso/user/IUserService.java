package com.empathy.api.service.sso.user;

import org.springframework.stereotype.Repository;

import com.empathy.model.sso.user.User;

@Repository
public interface IUserService {

	User findById(String userID);

}
