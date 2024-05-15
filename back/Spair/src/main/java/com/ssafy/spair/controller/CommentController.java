package com.ssafy.spair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spair.model.dto.Comment;
import com.ssafy.spair.model.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	private final CommentService commentService ;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService ;
	}
	
	// 댓글 등록
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Comment comment) {
		return new ResponseEntity<>(commentService.insert(comment), HttpStatus.CREATED) ;
	}

}
