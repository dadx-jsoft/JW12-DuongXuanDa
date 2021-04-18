package com.devpro.shopdoda.entities.blog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devpro.shopdoda.entities.BaseEntity;
import com.devpro.shopdoda.entities.Review;

@Entity
@Table(name = "tbl_blog")
public class Blog extends BaseEntity {
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "avatar", nullable = true)
	private String avatar;

	@Column(name = "short_description")
	private String shortDescription;

	@Lob
	@Column(name = "detail_description", columnDefinition = "LONGTEXT", nullable = true)
	private String detailDescription;

	@Column(name = "views")
	private int views;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blog_type_id")
	private BlogType blogType;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "blog", fetch = FetchType.LAZY)
	private List<BlogComment> comments = new ArrayList<BlogComment>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}

	public List<BlogComment> getComments() {
		return comments;
	}

	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

}
