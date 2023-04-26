package com.eoptech.shopdoda.entities;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tbl_saleorder_products database table.
 * 
 */
@Getter
@Setter
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

}