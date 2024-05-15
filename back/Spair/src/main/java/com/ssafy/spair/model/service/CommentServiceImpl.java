package com.ssafy.spair.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spair.model.dao.CommentDao;
import com.ssafy.spair.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentDao commentDao ;
	
	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao ;
	}

	@Override
	public int insert(Comment comment) {
		return commentDao.insert(comment);
	}

}
