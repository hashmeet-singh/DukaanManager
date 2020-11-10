package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Order;
import com.rrdlabs.dukaanmanager.entities.OrderItem;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.OrderItemRepository;
import com.rrdlabs.dukaanmanager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(Order order) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(int orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Order Id: " + orderId));
    }

    @Override
    public List<Order> getAllOrdersOfCustomer(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }
}
