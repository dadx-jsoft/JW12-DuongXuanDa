package com.devpro.buoi1.entities;

import javax.persistence.*;

/**
 * The persistent class for the tbl_saleorder_products database table.
 * 
 */
@Entity
@Table(name = "tbl_saleorder_products")
public class SaleorderProduct extends BaseEntity {

	@Column(name = "product_id", nullable = false)
	private Integer productId;

	@Column(nullable = false)
	private int quantity;

	// bi-directional many-to-one association to Saleorder
	@ManyToOne
	@JoinColumn(name = "saleorder_id", nullable = false)
	private Saleorder saleorder;

	public SaleorderProduct() {
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Saleorder getSaleorder() {
		return saleorder;
	}

	public void setSaleorder(Saleorder saleorder) {
		this.saleorder = saleorder;
	}

}