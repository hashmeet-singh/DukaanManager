package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.dto.OrderItemDto;
import com.rrdlabs.dukaanmanager.entities.*;
import com.rrdlabs.dukaanmanager.exceptions.QuantityExceededException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.OrderItemRepository;
import com.rrdlabs.dukaanmanager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ProductService productService;

    private final CustomerService customerService;

    private final StaffService staffService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductService productService, CustomerService customerService, StaffService staffService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.staffService = staffService;
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        List<OrderItemDto> orderItemDtos = orderRequest.getOrderItems();
        validateProducts(orderItemDtos);
        Customer customer = customerService.getCustomer(orderRequest.getCustomerId());
        Staff staff = staffService.getStaff(orderRequest.getStaffId());
        if (Objects.isNull(customer) || Objects.isNull(staff))
            throw new RecordNotFoundException("Invalid Customer or Staff ID");

        Order order = new Order(OrderStatus.PENDING, customer, staff, new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto dto : orderItemDtos) {
            Product product = productService.getProduct(dto.getProduct().getId());
            if (product.getQuantity() < dto.getQuantity()) {
                throw new QuantityExceededException("Quantity limit exceeded./nMax quantity available for Product Id: " + product.getId() + " is: " + product.getQuantity());
            }

            product.setQuantity(product.getQuantity() - dto.getQuantity());
            productService.updateProduct(product);
            OrderItem orderItem = new OrderItem(order, product, dto.getQuantity(), dto.getSellingPrice());
            orderItems.add(createOrderItem(orderItem));
        }

        order.setOrderItems(orderItems);
        return update(order);
    }

    private void validateProducts(List<OrderItemDto> orderItems) {
        List<OrderItemDto> items = orderItems
                .stream()
                .filter(item -> Objects.isNull(productService.getProduct(item.getProduct().getId())))
                .collect(Collectors.toList());

        if (!items.isEmpty()) {
            throw new RecordNotFoundException("Product not found.");
        }

    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Order Id: " + orderId));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getCustomerOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }
}
