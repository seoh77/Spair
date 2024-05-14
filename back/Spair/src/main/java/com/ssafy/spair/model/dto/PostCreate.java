package com.ssafy.spair.model.dto;

public class PostCreate {
	
	private Post post ;
	private SportsCenter sportsCenter ;
	
	public PostCreate() {
	}

	public PostCreate(Post post, SportsCenter sportsCenter) {
		this.post = post;
		this.sportsCenter = sportsCenter;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}

	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}

	@Override
	public String toString() {
		return "PostCreate [post=" + post + ", sportsCenter=" + sportsCenter + "]";
	}
}
