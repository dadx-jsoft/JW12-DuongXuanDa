package com.eoptech.shopdoda.dto.search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class ProductSearch {

	private String seo;
	private String categorySeo;
	public String searchText;

	public Integer offset = null;
	public Integer count = null;

	public void buildPaging(HttpServletRequest request) {
		if (!StringUtils.isEmpty(request.getParameter("offset"))) {
			this.offset = Integer.parseInt(request.getParameter("offset"));
		} else {
			this.offset = 0;
		}
	}

	public Integer getOffset() {
		return offset;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public String getCategorySeo() {
		return categorySeo;
	}

	public void setCategorySeo(String categorySeo) {
		this.categorySeo = categorySeo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
