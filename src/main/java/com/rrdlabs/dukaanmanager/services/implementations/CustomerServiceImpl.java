package com.rrdlabs.dukaanmanager.services.implementations;

import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.CustomerRepository;
import com.rrdlabs.dukaanmanager.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) throws RecordNotFoundException {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Customer id: " + id));
    }

    @Override
    public void validateCustomer(Customer customer) {
        if (!customer.getEmail().isEmpty() && !customerRepository.findByEmail(customer.getEmail()).isEmpty()) {
            throw new KeyColumnDuplicationException("Email address: " + customer.getEmail() + " is already registered.");
        }

        if (!customerRepository.findByPhoneNo(customer.getPhoneNo()).isEmpty()) {
            throw new KeyColumnDuplicationException("Phone Number: " + customer.getPhoneNo() + " is already registered.");
        }
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
