package com.ssafy.spair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spair.model.dto.User;
import com.ssafy.spair.model.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private final UserService userService ;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService ;
	}
	
	// 회원가입
	@PostMapping("join")
	public ResponseEntity<?> join(@RequestBody User user) {
		return new ResponseEntity<>(userService.join(user), HttpStatus.CREATED);
	}
	

}
