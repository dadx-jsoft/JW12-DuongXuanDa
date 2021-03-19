package com.devpro.shopdoda.entities;

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

	@Column(name = "quality", nullable = false)
	private int quantity;

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

	public Saleorder getSaleorder() {
		return saleorder;
	}

	public void setSaleorder(Saleorder saleorder) {
		this.saleorder = saleorder;
	}

}