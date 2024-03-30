package com.esewashopping.order.conroller;

import com.esewashopping.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeTheOrde(@RequestParam Integer cid) {
        return ResponseEntity.ok(orderService.placeOrder(cid));
    }

}
