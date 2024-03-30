package com.esewashopping.order.service.impl;

import com.esewashopping.cart.repo.CartItemRepo;
import com.esewashopping.cart.repo.CartRepo;
import com.esewashopping.customer.CustomerRepo;
import com.esewashopping.order.dto.OrderDTO;
import com.esewashopping.order.repo.OrderRepo;
import com.esewashopping.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final CustomerRepo customerRepo;

    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;


    @Override
    public OrderDTO placeOrder(Integer customerId) {
        return null;
    }
}

