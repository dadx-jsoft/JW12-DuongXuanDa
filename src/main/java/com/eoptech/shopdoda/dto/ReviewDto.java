package com.eoptech.shopdoda.dto;

public class ReviewDto {
	private Integer userId;
	private Integer productOrBlogId;
	private String message;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductOrBlogId() {
		return productOrBlogId;
	}

	public void setProductOrBlogId(Integer productOrBlogId) {
		this.productOrBlogId = productOrBlogId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
