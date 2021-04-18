package com.devpro.shopdoda.entities.blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devpro.shopdoda.entities.BaseEntity;
import com.devpro.shopdoda.entities.User;

@Entity
@Table(name = "tbl_blog_comment")
public class BlogComment extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "comment")
	private String comment;

	@Column(name = "parent_comment_id")
	private int parentCommentId;

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(int parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

}
