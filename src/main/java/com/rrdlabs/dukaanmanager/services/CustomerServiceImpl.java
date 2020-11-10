package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invalid Customer id: " + id));
    }

    @Override
    public boolean validateCustomer(Customer customer) {

        if (!customer.getEmail().isEmpty() && !customerRepository.findByEmail(customer.getEmail()).isEmpty()) {
            throw new KeyColumnDuplicationException("Email address: " + customer.getEmail() + " is already registered.");
        }

        if (!customerRepository.findByPhoneNo(customer.getPhoneNo()).isEmpty()) {
            throw new KeyColumnDuplicationException("Phone Number: " + customer.getPhoneNo() + " is already registered.");
        }
        return true;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
