package com.ssafy.spair.model.service;

import com.ssafy.spair.model.dto.Comment;

public interface CommentService {
	
	int insert(Comment comment) ;
	
	int modify(Comment comment) ;

	int delete(int commentId) ;

}
