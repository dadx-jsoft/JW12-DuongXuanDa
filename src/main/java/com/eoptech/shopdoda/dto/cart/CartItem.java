package com.eoptech.shopdoda.dto.cart;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    
	private int productId;
	private String productName;
	private String productAvatar;
	private int quantity;
	private BigDecimal priceUnit;
	private String productSeo;

}
