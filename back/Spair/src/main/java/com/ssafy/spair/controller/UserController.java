package com.ssafy.spair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spair.model.dto.User;
import com.ssafy.spair.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "user-controller", description = "회원 API")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
	
	private final UserService userService ;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService ;
	}
	
	// 회원가입
	@PostMapping("join")
	@Operation(summary = "회원가입")
	public ResponseEntity<?> join(@RequestBody User user) {
		return new ResponseEntity<>(userService.join(user), HttpStatus.CREATED);
	}
	
	// ID 중복확인
	@GetMapping("check/id/{loginId}")
	@Operation(summary = "ID 중복확인")
	public ResponseEntity<?> checkId(@PathVariable("loginId") String loginId) {
		List<User> allUser = userService.searchAll() ;
		
		// 모든 user를 순회하며 기존 user loginId와 중복되는지 확인
		for(User user : allUser) {
			String curUserLoginId = user.getLoginId() ;		// 기존 user의 loginId
			
			if(curUserLoginId.equals(loginId)) {
				return new ResponseEntity<>("이미 존재하는 ID입니다.", HttpStatus.OK) ;
			}
		}
		
		return new ResponseEntity<>("사용 가능한 ID입니다.", HttpStatus.OK) ;
	}
	
	// 닉네임 중복확인
	@GetMapping("check/nickname/{nickname}")
	@Operation(summary = "닉네임 중복확인")
	public ResponseEntity<?> checkNickname(@PathVariable("nickname") String nickname) {
		
		List<User> allUser = userService.searchAll() ;
		
		// 모든 user를 순회하며 기존 user의 nickname와 중복되는지 확인
		for(User user : allUser) {
			String curUserNickname = user.getNickname() ;		// 기존 user의 nickname
			
			if(curUserNickname.equals(nickname)) {
				return new ResponseEntity<>("이미 존재하는 닉네임입니다.", HttpStatus.OK) ;
			}
		}
		
		return new ResponseEntity<>("사용 가능한 닉네임입니다.", HttpStatus.OK) ;
	}
	
	// 로그인
	@PostMapping("login")
	@Operation(summary = "로그인")
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
					// 로그인 성공시 반환할 데이터
					User responseUser = new User(curUser.getUserId(), curUser.getLoginId(), curUser.getNickname()) ;
					
					return new ResponseEntity<>(responseUser, HttpStatus.OK) ;
				}
				
				return new ResponseEntity<>("비밀번호가 올바르지 않습니다.", HttpStatus.OK) ;
			}
		}

		return new ResponseEntity<>("아이디가 존재하지 않습니다.", HttpStatus.OK) ;
	}

}
