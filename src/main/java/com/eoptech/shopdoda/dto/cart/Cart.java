package com.eoptech.shopdoda.dto.cart;

import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {

    private List<CartItem> cartItems = new ArrayList<CartItem>();
    private BigDecimal totalPrice = BigDecimal.ZERO;

}
