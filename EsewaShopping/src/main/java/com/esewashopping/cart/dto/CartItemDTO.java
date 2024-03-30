package com.esewashopping.cart.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Integer id;
    private Integer quantity;
    private Double totalPrice;
    private Integer customerId;
    private Integer productId;


}
