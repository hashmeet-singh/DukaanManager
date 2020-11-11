package com.rrdlabs.dukaanmanager.repositories;

import com.rrdlabs.dukaanmanager.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByPhoneNo(String phoneNo);

    List<Customer> findByEmail(String email);
}
