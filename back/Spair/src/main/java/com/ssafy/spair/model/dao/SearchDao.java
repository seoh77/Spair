package com.ssafy.spair.model.dao;

import java.util.List;

import com.ssafy.spair.model.dto.Post;

public interface SearchDao {
	
	List<Post> searchKeyword(String keyword) ;

}
