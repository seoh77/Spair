package com.ssafy.spair.model.dao;

import com.ssafy.spair.model.dto.SportsCenter;

public interface SportsCenterDao {
	
	// 스포츠센터 추가
	int insert(SportsCenter sportsCenter) ;
	
	// 위도, 경도 값을 입력받아 스포츠센터 찾기
	SportsCenter search(double latitude, double longitude) ;

}
