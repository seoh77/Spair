package com.ssafy.spair.model.service;

import com.ssafy.spair.model.dto.User;

public interface UserService {
	
	User search(int userId) ;

	int join(User user) ;

}
