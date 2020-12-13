package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.*;
import com.rrdlabs.dukaanmanager.entities.dto.OrderRequestDto;
import com.rrdlabs.dukaanmanager.services.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders;
    }

    @GetMapping("/{order_id}")
    public Order getOrderById(@PathVariable(name = "order_id") Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequestDto orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @PostMapping("/{order_id}/close")
    public Order closeOrder(@PathVariable(name = "order_id") Long id) {
        Order order = orderService.getOrder(id);
        order.setStatus(OrderStatus.COMPLETED);
        return orderService.update(order);
    }

    @PostMapping("/{order_id}/cancel")
    public Order cancelOrder(@PathVariable(name = "order_id") Long id) {
        return null;
    }


}
