package com.ssafy.spair.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spair.model.dao.SportsCenterDao;
import com.ssafy.spair.model.dto.SportsCenter;

@Service
public class SportsCenterServiceImpl implements SportsCenterService  {
	
	private SportsCenterDao sportsCenterDao ;
	
	@Autowired
	public SportsCenterServiceImpl(SportsCenterDao sportsCenterDao) {
		this.sportsCenterDao = sportsCenterDao ;
	}

	@Override
	public int insert(SportsCenter sportsCenter) {
		sportsCenterDao.insert(sportsCenter) ;
		SportsCenter result = sportsCenterDao.search(sportsCenter.getLatitude(), sportsCenter.getLongitude()) ;
		return result.getCenterId();
	}

	@Override
	public int search(double latitude, double longitude) {
		SportsCenter center = sportsCenterDao.search(latitude, longitude) ;
		
		if(center == null) {
			return 0 ;
		}
		
		return center.getCenterId();
	}

}
