package com.ssafy.spair.model.service;

import java.util.List;

import com.ssafy.spair.model.dto.Post;
import com.ssafy.spair.model.dto.SearchFilter;

public interface SearchService {
	
	List<Post> searchKeyword(String keyword) ;
	
	List<Post> filterSearch(SearchFilter searchFilter) ;

	List<Post> townFilterSearch(SearchFilter searchFilter) ;
	
	List<Post> searchCenterPost(int centerId) ;

}
