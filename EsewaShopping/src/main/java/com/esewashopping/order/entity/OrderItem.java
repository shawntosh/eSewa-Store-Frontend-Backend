package com.esewashopping.order.entity;

import com.esewashopping.product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order; // Owning side of the association

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // The product associated with this order item
}
