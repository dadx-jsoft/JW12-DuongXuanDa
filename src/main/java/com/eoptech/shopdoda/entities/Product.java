package com.eoptech.shopdoda.entities;

import java.math.BigDecimal;
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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_products")
public class Product extends BaseEntity {

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "price", precision = 13, scale = 2, nullable = false)
	private BigDecimal price;

	@Column(name = "price_sale", precision = 13, scale = 2, nullable = true)
	private BigDecimal priceSale;
	
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "short_description")
	private String shortDescription;

	@Lob
	@Column(name = "detail_description", columnDefinition = "LONGTEXT", nullable = true)
	private String detailDescription;

	@Column(name = "avatar", nullable = true)
	private String avatar;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category categories;

	@Column(name = "seo")
	private String seo;

	@Column(name = "color")
	private String color;

	@Column(name = "material")
	private String material;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private List<ProductsImages> productsImages = new ArrayList<ProductsImages>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<Review>();

	public ProductsImages addProductsImages(ProductsImages productsImages) {
		getProductsImages().add(productsImages);
		productsImages.setProduct(this);

		return productsImages;
	}

	public ProductsImages removeProductsImages(ProductsImages productsImages) {
		getProductsImages().remove(productsImages);
		productsImages.setProduct(null);

		return productsImages;
	}

}
