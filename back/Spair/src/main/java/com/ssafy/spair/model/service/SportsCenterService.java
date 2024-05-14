package com.ssafy.spair.model.service;

import com.ssafy.spair.model.dto.SportsCenter;

public interface SportsCenterService {

	// 스포츠센터 추가
	int insert(SportsCenter sportsCenter) ;
	
	// 스포츠센터 주소 검색
	int search(String address) ;
	
}
