package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomer(Long id) throws RecordNotFoundException;

    void validateCustomer(Customer customer);

    Customer createCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    Customer updateCustomer(Customer customer);
}
