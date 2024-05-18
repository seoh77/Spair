package com.ssafy.spair.model.dto;

import java.time.LocalDateTime;

public class Comment {
	
	private int commentId ;
	private int postId ;
	private int userId ;
	private String content ;
	private int status ;
	private LocalDateTime createdDate ;
	private LocalDateTime modifiedDate ;
	private Integer parentId ;
	
	private User user ;
	
	public Comment() {
		
	}
	
	public Comment(int postId, int userId, String content, int status, LocalDateTime createdDate,
			LocalDateTime modifiedDate, Integer parentId) {
		this.postId = postId;
		this.userId = userId;
		this.content = content;
		this.status = status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.parentId = parentId;
	}

	public Comment(int commentId, int postId, int userId, String content, int status, LocalDateTime createdDate,
			LocalDateTime modifiedDate, Integer parentId) {
		this.commentId = commentId;
		this.postId = postId;
		this.userId = userId;
		this.content = content;
		this.status = status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.parentId = parentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", postId=" + postId + ", userId=" + userId + ", content=" + content
				+ ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", parentId=" + parentId + "]";
	}
	
}
