package com.ssafy.spair.model.dto;

public class SearchFilter {
	
	private Integer status ;
	private Integer gender ;
	private String exerciseType ;
	private Integer minPrice ;
	private Integer maxPrice ;
	
	public SearchFilter() {
		
	}
	
    public SearchFilter(Integer status, Integer gender, String exerciseType, Integer minPrice, Integer maxPrice) {
		this.status = status;
		this.gender = gender;
		this.exerciseType = exerciseType;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
    
	public boolean isEmpty() {
        return status == null && gender == null && exerciseType == null && minPrice == null && maxPrice == null;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}
