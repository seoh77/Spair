package com.ssafy.spair.model.service;

import java.util.List;

import com.ssafy.spair.model.dto.Comment;

public interface CommentService {
	
	int insert(Comment comment) ;
	
	int modify(Comment comment) ;

	int delete(int commentId) ;
	
	List<Comment> search(int postId) ;

}
