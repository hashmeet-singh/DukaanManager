package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.dto.OrderItemDto;
import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.entities.Order;
import com.rrdlabs.dukaanmanager.entities.OrderItem;
import com.rrdlabs.dukaanmanager.entities.Staff;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.services.CustomerService;
import com.rrdlabs.dukaanmanager.services.OrderService;
import com.rrdlabs.dukaanmanager.services.ProductService;
import com.rrdlabs.dukaanmanager.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    private StaffService staffService;

    @PostMapping
    public Order createOrder(@RequestBody OrderForm orderForm) {
        List<OrderItemDto> orderItemDtos = orderForm.getOrderItems();
        validateProducts(orderItemDtos);
        validateCustomer(orderForm.getCustomer());
        validateStaff(orderForm.getStaff());
        Order order = new Order();
        order.setCustomer(orderForm.getCustomer());
        order.setStaff(orderForm.getStaff());
        order = orderService.createOrder(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto dto : orderItemDtos) {
            OrderItem orderItem = new OrderItem(order, productService.getProduct(dto.getProduct().getId()), dto.getQuantity(), dto.getSellingPrice(), "NEW");
            orderItems.add(orderService.createOrderItem(orderItem));
        }

        order.setOrderItems(orderItems);
        orderService.update(order);
        return order;
    }

    private void validateCustomer(Customer customer) {
        if (Objects.isNull(customerService.getCustomer(customer.getId()))) {
            throw new RecordNotFoundException("Invalid Customer Id: " + customer.getId());
        }
    }

    private void validateStaff(Staff staff) {
        if (Objects.isNull(staffService.getStaff(staff.getId()))) {
            throw new RecordNotFoundException("Invalid Customer Id: " + staff.getId());
        }
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

    private static class OrderForm {
        private Customer customer;
        private Staff staff;
        private List<OrderItemDto> orderItems;

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Staff getStaff() {
            return staff;
        }

        public void setStaff(Staff staff) {
            this.staff = staff;
        }

        public List<OrderItemDto> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemDto> orderItems) {
            this.orderItems = orderItems;
        }
    }
}
