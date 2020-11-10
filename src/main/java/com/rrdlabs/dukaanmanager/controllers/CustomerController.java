package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/add")
    public ResponseEntity createCustomer(@Valid @RequestBody Customer user) {
        if (customerService.validateCustomer(user)) {
            Customer createdCustomer = customerService.createCustomer(user);
            return ResponseEntity.ok("Customer created successfully with Id: " + createdCustomer.getId());
        }

        return new ResponseEntity("Validation Failed", HttpStatus.BAD_REQUEST);
    }
}
