package com.ssafy.spair.model.service;

import java.util.List;

import com.ssafy.spair.model.dto.Post;

public interface PostService {

	int insert(Post post) ;
	
	int modify(Post post) ;
	
	int delete(int postId) ;
	
	List<Post> searchAll() ;
	
	List<Post> townSearchAll(int latitude, int longitude) ;
	
	List<Post> search(int postId) ;
	
}
