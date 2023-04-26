package com.eoptech.shopdoda.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tbl_saleorder database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_saleorder")
public class Saleorder extends BaseEntity {

	@Column(nullable = false)
	private String code;

	@Column(name = "customer_address")
	private String customerAddress;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_phone")
	private String customerPhone;

	@Column(name = "cutomer_email")
	private String customerEmail;

	@Column(name = "seo")
	private String seo;
	
	@Column(name = "order_status")
	private Integer orderStatus;

	@Column(precision = 10, scale = 2)
	private BigDecimal total;

	@Column(name = "user_id")
	private Integer userId;

	// bi-directional many-to-one association to SaleorderProduct
	@OneToMany(mappedBy = "saleorder", cascade = CascadeType.ALL)
	private List<SaleorderProduct> saleorderProducts;

	public Saleorder() {
	}

	public SaleorderProduct addSaleorderProduct(SaleorderProduct saleorderProduct) {
		if(this.saleorderProducts == null) {
			this.saleorderProducts = new ArrayList<SaleorderProduct>();
		}
		getSaleorderProducts().add(saleorderProduct);
		saleorderProduct.setSaleorder(this);
		
		return saleorderProduct;
	}

	public SaleorderProduct removeSaleorderProduct(SaleorderProduct saleorderProduct) {
		getSaleorderProducts().remove(saleorderProduct);
		saleorderProduct.setSaleorder(null);

		return saleorderProduct;
	}

}