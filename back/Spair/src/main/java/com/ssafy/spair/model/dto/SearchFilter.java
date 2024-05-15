package com.ssafy.spair.model.dto;

public class SearchFilter {
	
	private Integer status ;
	private Integer gender ;
	private String exerciseType ;
	private Integer minPrice ;
	private Integer maxPrice ;
	private double latitude ;
	private double longitude ;
	
	public SearchFilter() {
		
	}
	
    public SearchFilter(Integer status, Integer gender, String exerciseType, Integer minPrice, Integer maxPrice) {
		this.status = status;
		this.gender = gender;
		this.exerciseType = exerciseType;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
    
	public SearchFilter(Integer status, Integer gender, String exerciseType, Integer minPrice, Integer maxPrice,
			double latitude, double longitude) {
		this.status = status;
		this.gender = gender;
		this.exerciseType = exerciseType;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
