package com.esewashopping.order.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {

    private Integer orderId;
    private String orderStatus;
    private String paymentStatus;
    private LocalDate orderDelivery;
    private Double totalAmount;
    private String billingAddress;
    private Integer customerId;
}
