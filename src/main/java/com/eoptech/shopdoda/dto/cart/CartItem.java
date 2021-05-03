package com.eoptech.shopdoda.dto.cart;

import java.math.BigDecimal;

public class CartItem {
	private int productId;
	private String productName;
	private String productAvatar;
	private int quantity;
	private BigDecimal priceUnit;
	private String productSeo;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(BigDecimal priceUnit) {
		this.priceUnit = priceUnit;
	}

	public String getProductAvatar() {
		return productAvatar;
	}

	public void setProductAvatar(String productAvatar) {
		this.productAvatar = productAvatar;
	}

	public String getProductSeo() {
		return productSeo;
	}

	public void setProductSeo(String productSeo) {
		this.productSeo = productSeo;
	}

}
