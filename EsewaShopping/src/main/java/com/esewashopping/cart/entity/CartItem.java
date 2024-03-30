package com.esewashopping.cart.entity;

import com.esewashopping.customer.Customer;
import com.esewashopping.product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartItemId;

    private Integer quantity;
    private float totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;


}
