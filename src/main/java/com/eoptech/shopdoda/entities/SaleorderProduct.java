package com.eoptech.shopdoda.entities;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The persistent class for the tbl_saleorder_products database table.
 * 
 */
@Entity
@Table(name = "tbl_saleorder_products")
public class SaleorderProduct extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	Product product;

	@Column(name = "quantity", nullable = false)
	private int quantity;
	@Column(name = "price_unit", nullable = false)
	private BigDecimal priceUnit;

	// bi-directional many-to-one association to Saleorder
	@ManyToOne
	@JoinColumn(name = "saleorder_id", nullable = false)
	private Saleorder saleorder;

	public SaleorderProduct() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public Saleorder getSaleorder() {
		return saleorder;
	}

	public void setSaleorder(Saleorder saleorder) {
		this.saleorder = saleorder;
	}

}