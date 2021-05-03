package com.eoptech.shopdoda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_products_images")
public class ProductsImages extends BaseEntity {
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "path", nullable = false)
	private String path;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
