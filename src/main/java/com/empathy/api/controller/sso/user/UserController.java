package com.empathy.api.controller.sso.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.sso.user.IUserService;
import com.empathy.model.sso.user.User;

@RestController
@RequestMapping("/sso/user")
public class UserController {
	@Autowired
	private IUserService service;

	// create
	@PostMapping("/a")
	public void post(@Valid @RequestBody User user, HttpServletRequest request) throws Exception {
		user.setUserID(null);
		service.save(user);
	}

	// read
	@GetMapping("/a/{userID}")
	public User get(@PathVariable String userID, HttpServletRequest request) throws Exception {

		return service.findById(userID);
	}

	// update
	@PutMapping("/a/{userID}")
	public void put(@PathVariable String userID, @Valid @RequestBody User user, HttpServletRequest request)
			throws Exception {

		user.setUserID(userID);
		service.save(user);
	}

	// delete
	@DeleteMapping("/a/{userID}")
	public void deleteProject(@PathVariable String userID, HttpServletRequest request) throws Exception {
		User user = new User();
		user.setUserID(userID);
		service.delete(user);
	}

	// list
	@GetMapping("/a/find")
	public List<User> getAll(HttpServletRequest request) throws Exception {
		return service.findAll();
	}
}
