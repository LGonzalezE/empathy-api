package com.empathy.api.service.sso.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empathy.model.sso.user.User;
import com.empathy.repository.sso.user.UserRepository;

@Service
public class  UserService implements IUserService{
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAll() {
		return (List<User>) repository.findAll();

	}

	@Override
	public User save(User user) throws Exception {

		return repository.save(user);
	}

	@Override
	public void delete(User user) throws Exception {

		if (!repository.existsById(user.getUserID())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		repository.deleteById(user.getUserID());
	}

	@Override
	public User findById(String userID) {

		if (!repository.existsById(userID)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.findById(userID).get();
	}

	@Override
	public Boolean existsById(String userID) {
		return repository.existsById(userID);
	}

}
