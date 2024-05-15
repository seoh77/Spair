package com.ssafy.spair.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spair.model.dao.PostDao;
import com.ssafy.spair.model.dto.Post;

@Service
public class PostServiceImpl implements PostService {
	
	private PostDao postDao ;
	
	@Autowired
	public PostServiceImpl(PostDao postDao) {
		this.postDao = postDao ;
	}

	@Override
	public int insert(Post post) {
		return postDao.insert(post);
	}

	@Override
	public int modify(Post post) {
		return postDao.modify(post);
	}

	@Override
	public int delete(int postId) {
		return postDao.delete(postId);
	}

	@Override
	public List<Post> searchAll() {
		return postDao.searchAll();
	}

	@Override
	public List<Post> townSearchAll(double latitude, double longitude) {
		return postDao.townSearchAll(latitude, longitude);
	}

	@Override
	public Post search(int postId) {
		return postDao.search(postId);
	}

}
