package com.esewashopping.order.service;

import com.esewashopping.order.dto.OrderDTO;


public interface OrderService {

    OrderDTO placeOrder(Integer customerId);

}
