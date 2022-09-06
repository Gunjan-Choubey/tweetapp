package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.RegistrationDTO;
import com.tweetapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String registerUser(@RequestBody RegistrationDTO register) {
		return userService.registerUser(register);
	}

	@GetMapping("/login")
	public boolean loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		return userService.loginUser(username, password);
	}

	@GetMapping("/{username}/forgot")
	public String forgotPassword(@PathVariable String username, @RequestParam("password") String password) {
		return userService.forgotPassword(username, password);
	}

	@GetMapping(value = "/users/all")
	public List<RegistrationDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(value = "/user/search/")
	public RegistrationDTO searchByUsername(@RequestParam("username") String username) {
		return userService.searchByUsername(username);
	}

}
