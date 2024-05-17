package com.ssafy.spair.model.dto;

public class User {
	
	private int userId ;
	private String loginId ;
	private String password ;
	private String nickname ;
	private String name ;
	private int gender ;
	private String address ;
	private double latitude ;
	private double longitude ;
	
	public User() {
		
	}
	
	// 아이디 확인
	public User(String loginId) {
		this.loginId = loginId ;
	}
	
	// 회원가입 
	public User(String loginId, String password, String nickname, String name, int gender, String address,
			double latitude, double longitude) {
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	// user 정보 조회
	public User(int userId, String loginId, String password, String nickname, String name, int gender, String address,
			double latitude, double longitude) {
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginId=" + loginId + ", password=" + password + ", nickname=" + nickname
				+ ", name=" + name + ", gender=" + gender + ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
