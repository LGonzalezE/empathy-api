package com.empathy.api.controller.sso.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.api.service.sso.user.IUserService;
import com.empathy.model.sso.user.User;

@RestController
@RequestMapping("/sso/user")
public class UserController {
	@Autowired
	private IUserService service;

	
	// read
	@GetMapping("/{userID}")
	public User get(@PathVariable String userID, HttpServletRequest request) throws Exception {

		return service.findById(userID);
	}

	
}
