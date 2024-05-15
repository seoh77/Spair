package com.ssafy.spair.model.dto;

public class SearchFilter {
	
	private int status ;
	private int gender ;
	private String exerciseType ;
	private int minPrice ;
	private int maxPrice ;
	
	public SearchFilter() {
	}

	public SearchFilter(int status, int gender, String exerciseType, int minPrice, int maxPrice) {
		this.status = status;
		this.gender = gender;
		this.exerciseType = exerciseType;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}
