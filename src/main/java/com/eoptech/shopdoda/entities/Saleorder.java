package com.eoptech.shopdoda.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tbl_saleorder database table.
 * 
 */
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<SaleorderProduct> getSaleorderProducts() {
		return saleorderProducts;
	}

	public void setSaleorderProducts(List<SaleorderProduct> saleorderProducts) {
		this.saleorderProducts = saleorderProducts;
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