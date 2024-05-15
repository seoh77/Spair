package com.ssafy.spair.model.dao;

import com.ssafy.spair.model.dto.SportsCenter;

public interface SportsCenterDao {
	
	// 스포츠센터 추가
	int insert(SportsCenter sportsCenter) ;
	
	// 스포츠센터 주소를 입력받아 스포츠센터 찾기
	SportsCenter search(String address) ;

}
