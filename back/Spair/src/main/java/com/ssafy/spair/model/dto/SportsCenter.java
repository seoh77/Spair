package com.ssafy.spair.model.dto;

public class SportsCenter {
	
	private int centerId ;
	private String roadAddress ;
	private String localAddress ;
	private double latitude ;
	private double longitude ;
	
	public SportsCenter() {
	}

	public SportsCenter(int centerId, String roadAddress, String localAddress, double latitude, double longitude) {
		super();
		this.centerId = centerId;
		this.roadAddress = roadAddress;
		this.localAddress = localAddress;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
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
