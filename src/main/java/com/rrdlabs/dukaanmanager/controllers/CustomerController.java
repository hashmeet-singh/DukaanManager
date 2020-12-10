package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.entities.Order;
import com.rrdlabs.dukaanmanager.services.CustomerService;
import com.rrdlabs.dukaanmanager.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customer_id}")
    public Customer getCustomerById(@PathVariable(name = "customer_id") Long id) {
        return customerService.getCustomer(id);
    }

    // TODO: 10-12-2020 Implement logic for API
    @GetMapping("/{customer_id}/orders")
    public List<Order> getCustomerOrders(@PathVariable(name = "customer_id") Long id) {
        return orderService.getCustomerOrders(id);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer user) {
        customerService.validateCustomer(user);
        Customer createdCustomer = customerService.createCustomer(user);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customer_id}")
    public Customer updateCustomer(@PathVariable(name = "customer_id") Long id, @Valid @RequestBody Customer customer) {
        customerService.getCustomer(id);
        customer.setId(id);
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{customer_id}")
    public void deleteCustomer(@PathVariable(name = "customer_id") Long id) {
        customerService.deleteCustomer(id);
    }
}
