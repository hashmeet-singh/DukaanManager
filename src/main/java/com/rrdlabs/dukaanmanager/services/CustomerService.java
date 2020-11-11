package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    boolean validateCustomer(Customer customer);

    Customer createCustomer(Customer customer);
}
