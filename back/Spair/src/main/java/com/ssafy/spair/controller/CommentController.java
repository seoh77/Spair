package com.ssafy.spair.controller;

import java.time.LocalDateTime;

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
		
		if(comment.getCreatedDate() == null) {
			comment.setCreatedDate(LocalDateTime.now());
		}
		
		return new ResponseEntity<>(commentService.insert(comment), HttpStatus.CREATED) ;
	}
	
	// 댓글 수정
	@PutMapping("{commentId}")
	public ResponseEntity<?> modify(@PathVariable("commentId") int commentId, @RequestBody Comment comment) {
		comment.setCommentId(commentId);
		comment.setModifiedDate(LocalDateTime.now());
		return new ResponseEntity<>(commentService.modify(comment), HttpStatus.OK) ;
	}
	
	// 댓글 삭제
	@DeleteMapping("{commentId}")
	public ResponseEntity<?> delete(@PathVariable("commentId") int commentId) {
		return new ResponseEntity<>(commentService.delete(commentId), HttpStatus.OK) ;
	}
	
	// 특정 게시글에 해당하는 댓글 조회
	@GetMapping("{postId}")
	public ResponseEntity<?> search(@PathVariable("postId") int postId) {
		return new ResponseEntity<>(commentService.search(postId), HttpStatus.OK) ;
	}

}
