package com.eoptech.shopdoda.entities.blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.eoptech.shopdoda.entities.BaseEntity;
import com.eoptech.shopdoda.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
