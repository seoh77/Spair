package com.ssafy.spair.model.service;

import java.util.List;

import com.ssafy.spair.model.dto.Post;

public interface SearchService {
	
	List<Post> searchKeyword(String keyword) ;

}
