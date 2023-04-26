package com.eoptech.shopdoda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
	
	private Integer userId;
	private Integer productOrBlogId;
	private String message;

}
