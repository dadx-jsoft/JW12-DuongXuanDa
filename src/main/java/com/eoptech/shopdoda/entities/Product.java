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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceSale() {
		return priceSale;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPriceSale(BigDecimal priceSale) {
		this.priceSale = priceSale;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Category getCategories() {
		return categories;
	}

	public void setCategories(Category categories) {
		this.categories = categories;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public List<ProductsImages> getProductsImages() {
		return productsImages;
	}

	public void setProductsImages(List<ProductsImages> productsImages) {
		this.productsImages = productsImages;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public List<Review> getComments() {
		return reviews;
	}

	public void setComments(List<Review> comments) {
		this.reviews = comments;
	}

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
