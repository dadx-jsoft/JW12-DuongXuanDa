package com.devpro.shopdoda.entities.blog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devpro.shopdoda.entities.BaseEntity;

@Entity
@Table(name = "tbl_blog_type")
public class BlogType extends BaseEntity {
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "seo", nullable = true)
	private String seo;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private List<BlogType> childs = new ArrayList<BlogType>();

	// OneToMany mà có quan hệ 2 chiều, thì cần thêm 2 phương thức add và remove
	// để tránh lỗi, cần thêm method add, remove
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "blogType", fetch = FetchType.LAZY)
	private List<Blog> blogs = new ArrayList<Blog>();

	private void addBLog(Blog blog) {
		this.blogs.add(blog);
		blog.setBlogType(this);
	}

	private void removeBLog(Blog blog) {
		this.blogs.remove(blog);
		blog.setBlogType(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public List<BlogType> getChilds() {
		return childs;
	}

	public void setChilds(List<BlogType> childs) {
		this.childs = childs;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

}
