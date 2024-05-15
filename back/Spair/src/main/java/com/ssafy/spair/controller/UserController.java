package com.ssafy.spair.controller;

import java.util.List;

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
	
	// ID 중복확인
	@PostMapping("check/id")
	public ResponseEntity<?> checkId(@RequestBody String loginId) {
		
		List<User> allUser = userService.searchAll() ;
		
		// 모든 user를 순회하며 기존 user loginId와 중복되는지 확인
		for(User user : allUser) {
			String curUserLoginId = user.getLoginId() ;		// 기존 user의 loginId
			
			if(curUserLoginId.equals(loginId)) {
				return new ResponseEntity<>("이미 존재하는 ID입니다", HttpStatus.OK) ;
			}
		}
		
		return new ResponseEntity<>("사용 가능한 ID입니다.", HttpStatus.OK) ;
	}
	
	// 닉네임 중복확인
	@PostMapping("check/nickname")
	public ResponseEntity<?> checkNickname(@RequestBody String nickname) {
		
		List<User> allUser = userService.searchAll() ;
		
		// 모든 user를 순회하며 기존 user의 nickname와 중복되는지 확인
		for(User user : allUser) {
			String curUserNickname = user.getNickname() ;		// 기존 user의 nickname
			
			if(curUserNickname.equals(nickname)) {
				return new ResponseEntity<>("이미 존재하는 닉네임입니다", HttpStatus.OK) ;
			}
		}
		
		return new ResponseEntity<>("사용 가능한 닉네임입니다.", HttpStatus.OK) ;
	}
	
	// 로그인
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody User user) {
		
		String loginId = user.getLoginId() ;
		String password = user.getPassword() ;
		
		List<User> allUser = userService.searchAll() ;
		
		// 모든 user를 순회하며 loginId가 있는지 먼저 탐색
		for(User curUser : allUser) {
			String curUserId = curUser.getLoginId() ;
			
			if(curUserId.equals(loginId)) {
				// loginId가 있으면 비밀번호 비교
				String curPassword = curUser.getPassword() ;
				
				if(curPassword.equals(password)) {
					return new ResponseEntity<>("로그인 성공", HttpStatus.OK) ;
				}
				
				return new ResponseEntity<>("비밀번호가 올바르지 않습니다.", HttpStatus.OK) ;
			}
		}

		return new ResponseEntity<>("아이디가 존재하지 않습니다.", HttpStatus.OK) ;
	}

}
