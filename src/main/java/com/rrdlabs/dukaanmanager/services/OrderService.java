package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Order;
import com.rrdlabs.dukaanmanager.entities.OrderItem;
import com.rrdlabs.dukaanmanager.entities.dto.OrderRequestDto;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order cancelOrder(Order order);

    Order createOrder(OrderRequestDto orderRequest);

    Order getOrder(Long orderId);

    List<Order> getAllOrders();

    List<Order> getCustomerOrders(Long customerId);

    List<Order> getStaffOrders(Long staffId);

    OrderItem createOrderItem(OrderItem orderItem);

    Order update(Order order);
}
