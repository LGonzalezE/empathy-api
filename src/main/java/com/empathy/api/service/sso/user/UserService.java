package com.empathy.api.service.sso.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.sso.user.User;
import com.empathy.repository.sso.user.UserRepository;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository repository;

	@Override
	public User findById(String userID) {

		if (!repository.existsById(userID)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.findById(userID).get();
	}

}
