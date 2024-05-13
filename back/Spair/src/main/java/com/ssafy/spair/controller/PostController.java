package com.ssafy.spair.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.ssafy.spair.model.service.PostService;

@RestController
@RequestMapping("/api/board")
public class PostController {
	
	private final PostService postService ;
	
	@Autowired
	public PostController (PostService postService) {
		this.postService = postService ;
	}
	
	// 게시글 등록
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Post post){
		if(post.getCreatedDate() == null) {
			post.setCreatedDate(LocalDateTime.now());
		}
		return new ResponseEntity<>(postService.insert(post), HttpStatus.CREATED) ;
	}
	
	// 게시글 수정
	@PutMapping("{postId}")
	public ResponseEntity<?> modify(@PathVariable("postId") int postId, @RequestBody Post post) {
		post.setModifiedDate(LocalDateTime.now()) ;
		return new ResponseEntity<>(postService.modify(post), HttpStatus.OK) ;
	}
	
	// 게시글 삭제
	@DeleteMapping("{postId}")
	public ResponseEntity<?> delete(@PathVariable("postId") int postId) {
		return new ResponseEntity<>(postService.delete(postId), HttpStatus.OK) ;
	}
	
	// 전체 게시글 가져오기 (최신순 정렬)
	@GetMapping
	public ResponseEntity<?> searchAll() {
		return new ResponseEntity<>(postService.searchAll(), HttpStatus.OK) ;
	}
	
	// 우리 동네에 등록된 게시글 가져오기 (최신순 정렬)
	@GetMapping("town")
	public ResponseEntity<?> townSearchAll(@RequestParam double latitude, @RequestParam double longitude) {
		return new ResponseEntity<>(postService.townSearchAll(latitude, longitude), HttpStatus.OK) ;
	}
	
	// 특정 게시글 상세보기
	@GetMapping("{postId}")
	public ResponseEntity<?> search(@PathVariable("postId") int postId) {
		return new ResponseEntity<>(postService.search(postId), HttpStatus.OK) ;
	}

}
