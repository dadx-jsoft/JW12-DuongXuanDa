package com.eoptech.shopdoda.entities.blog;

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

import com.eoptech.shopdoda.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	@Column(name = "seo")
	private String seo;

}
