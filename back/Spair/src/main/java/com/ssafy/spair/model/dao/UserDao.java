package com.ssafy.spair.model.dao;

import com.ssafy.spair.model.dto.User;

public interface UserDao {
	
	User search(int userId) ;
	
	int join(User user) ;

}
