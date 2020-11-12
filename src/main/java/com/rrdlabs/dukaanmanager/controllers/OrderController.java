package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.dto.OrderItemDto;
import com.rrdlabs.dukaanmanager.entities.*;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.services.CustomerService;
import com.rrdlabs.dukaanmanager.services.OrderService;
import com.rrdlabs.dukaanmanager.services.ProductService;
import com.rrdlabs.dukaanmanager.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }


}
