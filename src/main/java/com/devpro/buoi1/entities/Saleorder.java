package com.devpro.buoi1.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the tbl_saleorder database table.
 * 
 */
@Entity
@Table(name = "tbl_saleorder")
public class Saleorder extends BaseEntity {

	@Column(nullable = false, length = 45)
	private String code;

	@Column(name = "customer_address", length = 100)
	private String customerAddress;

	@Column(name = "customer_name", length = 100)
	private String customerName;

	@Column(name = "customer_phone", length = 100)
	private String customerPhone;

	@Column(name = "cutomer_email", length = 100)
	private String cutomerEmail;

	@Column(length = 200)
	private String seo;

	@Column(precision = 10, scale = 2)
	private BigDecimal total;

	@Column(name = "user_id")
	private Integer userId;

	// bi-directional many-to-one association to SaleorderProduct
	@OneToMany(mappedBy = "saleorder")
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

	public String getCutomerEmail() {
		return cutomerEmail;
	}

	public void setCutomerEmail(String cutomerEmail) {
		this.cutomerEmail = cutomerEmail;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
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

	public SaleorderProduct addTblSaleorderProduct(SaleorderProduct saleorderProduct) {
		getSaleorderProducts().add(saleorderProduct);
		saleorderProduct.setSaleorder(this);

		return saleorderProduct;
	}

	public SaleorderProduct removeTblSaleorderProduct(SaleorderProduct saleorderProduct) {
		getSaleorderProducts().remove(saleorderProduct);
		saleorderProduct.setSaleorder(null);

		return saleorderProduct;
	}

}