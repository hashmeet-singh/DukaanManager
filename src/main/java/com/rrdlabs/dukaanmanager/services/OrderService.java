package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Order;
import com.rrdlabs.dukaanmanager.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrder(Long orderId);

    List<Order> getAllOrders();

    List<Order> getAllOrdersOfCustomer(Long customerId);

    OrderItem createOrderItem(OrderItem orderItem);

    Order update(Order order);
}
