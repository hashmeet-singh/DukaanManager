package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Order;
import com.rrdlabs.dukaanmanager.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrder(int orderId);

    List<Order> getAllOrdersOfCustomer(int customerId);

    OrderItem createOrderItem(OrderItem orderItem);

    Order update(Order order);
}
