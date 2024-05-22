package com.ssafy.spair.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spair.model.dto.Post;
import com.ssafy.spair.model.dto.PostCreate;
import com.ssafy.spair.model.dto.SportsCenter;
import com.ssafy.spair.model.dto.User;
import com.ssafy.spair.model.service.PostService;
import com.ssafy.spair.model.service.SportsCenterService;
import com.ssafy.spair.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/board")
@Tag(name = "post-controller", description = "게시글 CRUD API")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {
	
	private final PostService postService ;
	private final SportsCenterService sportsCenterService ;
	private final UserService userService ;
	
	@Autowired
	public PostController (PostService postService, SportsCenterService sportsCenterService, UserService userService) {
		this.postService = postService ;
		this.sportsCenterService = sportsCenterService ;
		this.userService = userService ;
	}
	
	// 게시글 등록
	@PostMapping
	@Operation(summary = "게시글 등록")
	public ResponseEntity<?> insert(@RequestBody PostCreate postCreate){
		Post post = postCreate.getPost() ;
		SportsCenter sportsCenter = postCreate.getSportsCenter() ;
		
		String address = sportsCenter.getRoadAddress() ;	
		int centerId = sportsCenterService.search(sportsCenter.getLatitude(), sportsCenter.getLongitude()) ;
		
		if(centerId == 0) {
			centerId = sportsCenterService.insert(sportsCenter) ;
		} 
		
		post.setCenterId(centerId);
		
		if(post.getCreatedDate() == null) {
			post.setCreatedDate(LocalDateTime.now());
		}
		return new ResponseEntity<>(postService.insert(post), HttpStatus.CREATED) ;
	}
	
	// 게시글 수정
	@PutMapping("{postId}")
	@Operation(summary = "게시글 수정")
	public ResponseEntity<?> modify(@PathVariable("postId") int postId, @RequestBody Post post) {
		post.setModifiedDate(LocalDateTime.now()) ;
		return new ResponseEntity<>(postService.modify(post), HttpStatus.OK) ;
	}
	
	// 게시글 삭제
	@DeleteMapping("{postId}")
	@Operation(summary = "게시글 삭제")
	public ResponseEntity<?> delete(@PathVariable("postId") int postId) {
		return new ResponseEntity<>(postService.delete(postId), HttpStatus.OK) ;
	}
	
	// 전체 게시글 가져오기 (최신순 정렬)
	@GetMapping
	@Operation(summary = "전체 게시글 조회", description = "전체 게시글을 조회하여 최신순으로 정렬")
	public ResponseEntity<?> searchAll() {
		return new ResponseEntity<>(postService.searchAll(), HttpStatus.OK) ;
	}
	
	// 우리 동네에 등록된 게시글 가져오기 (최신순 정렬)
	@GetMapping("town")
	@Operation(summary = "우리 동네 게시글 조회", description = "DB에 저장된 사용자 위치의 반경 1km 이내에 있는 sports center의 게시글만 조회")
	public ResponseEntity<?> townSearchAll(@RequestParam int userId) {
		
		User user = userService.search(userId) ;
		double latitude = user.getLatitude() ;
		double longitude = user.getLongitude() ;
		
		return new ResponseEntity<>(postService.townSearchAll(latitude, longitude), HttpStatus.OK) ;
	}
	
	// 특정 게시글 상세보기
	@GetMapping("{postId}")
	@Operation(summary = "특정 게시글 조회", description = "postId로 특정 게시글을 조회")
	public ResponseEntity<?> search(@PathVariable("postId") int postId) {
		return new ResponseEntity<>(postService.search(postId), HttpStatus.OK) ;
	}

}
