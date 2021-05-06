package com.eoptech.shopdoda.dto.search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getIdProductOrBlog() {
		return idProductOrBlog;
	}

	public void setIdProductOrBlog(Integer idProductOrBlog) {
		this.idProductOrBlog = idProductOrBlog;
	}

}
