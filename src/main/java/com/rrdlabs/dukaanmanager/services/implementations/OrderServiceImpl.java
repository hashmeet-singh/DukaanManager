package com.rrdlabs.dukaanmanager.services.implementations;

import com.rrdlabs.dukaanmanager.entities.dto.LineItemDto;
import com.rrdlabs.dukaanmanager.entities.*;
import com.rrdlabs.dukaanmanager.entities.dto.OrderRequestDto;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.OrderItemRepository;
import com.rrdlabs.dukaanmanager.repositories.OrderRepository;
import com.rrdlabs.dukaanmanager.services.CustomerService;
import com.rrdlabs.dukaanmanager.services.OrderService;
import com.rrdlabs.dukaanmanager.services.ProductService;
import com.rrdlabs.dukaanmanager.services.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Order createOrder(Order order) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Order cancelOrder(Order order) {
        Order cancelOrder = getOrder(order.getId());
        cancelOrder.setStatus(OrderStatus.CANCELLED);
        cancelOrder.getOrderItems().forEach((item) -> {
            productService.adjustProductQuantity(item.getProduct().getId(), item.getQuantity());
        });

        return orderRepository.save(cancelOrder);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Order createOrder(OrderRequestDto orderRequest) {
        List<LineItemDto> lineItemDtos = orderRequest.getLineItems();
        validateProducts(lineItemDtos);
        Customer customer = customerService.getCustomer(orderRequest.getCustomerId());
        Staff staff = staffService.getStaff(orderRequest.getStaffId());
        if (Objects.isNull(customer) || Objects.isNull(staff))
            throw new RecordNotFoundException("Invalid Customer or Staff ID");

        Order order = new Order(OrderStatus.PENDING, customer, staff, new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
        List<OrderItem> orderItems = new ArrayList<>();

        for (LineItemDto dto : lineItemDtos) {
            Product product = productService.adjustProductQuantity(dto.getProductId(), -1 * dto.getQuantity());
            OrderItem orderItem = new OrderItem(order, product, dto.getQuantity(), dto.getPrice());
            orderItems.add(createOrderItem(orderItem));
        }

        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    private void validateProducts(List<LineItemDto> orderItems) {
        List<LineItemDto> items = orderItems
                .stream()
                .filter(item -> Objects.isNull(productService.getProduct(item.getProductId())))
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
        return orderRepository.findByCustomer_Id(customerId);
    }

    @Override
    public List<Order> getStaffOrders(Long staffId) {
        return orderRepository.findByStaff_Id(staffId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Order update(Order order) {
        return orderRepository.save(order);
    }
}
