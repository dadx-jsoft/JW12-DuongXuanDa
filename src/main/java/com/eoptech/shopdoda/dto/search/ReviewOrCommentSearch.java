package com.eoptech.shopdoda.dto.search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewOrCommentSearch {

	public String searchText;

	public Integer offset = null;
	public Integer count = null;

	public Integer idProductOrBlog;

	public void buildPaging(HttpServletRequest request) {
		if (!StringUtils.isEmpty(request.getParameter("offset"))) {
			this.offset = Integer.parseInt(request.getParameter("offset"));
		} else {
			this.offset = 0;
		}
	}

}
