package com.ssafy.spair.model.service;

import java.util.List;

import com.ssafy.spair.model.dto.User;

public interface UserService {
	
	User search(int userId) ;

	int join(User user) ;

	List<User> searchAll() ;

}
