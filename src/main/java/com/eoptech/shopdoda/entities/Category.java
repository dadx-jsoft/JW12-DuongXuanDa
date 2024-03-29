package com.eoptech.shopdoda.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_category")
public class Category extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "seo", nullable = true)
	private String seo;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private List<Category> childs = new ArrayList<Category>();

	// OneToMany mà có quan hệ 2 chiều, thì cần thêm 2 phương thức add và remove
	// để tránh lỗi
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categories", fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<Product>();

	public void addProduct(Product product) {
		products.add(product);
		product.setCategories(this);
	}

	public void removeProduct(Product product) {
		products.remove(product);
		product.setCategories(null);
	}

}
