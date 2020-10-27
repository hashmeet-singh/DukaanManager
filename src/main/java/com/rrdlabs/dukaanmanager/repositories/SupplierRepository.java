package com.rrdlabs.dukaanmanager.repositories;

import com.rrdlabs.dukaanmanager.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    List<Supplier> findByEmail(String email);

    List<Supplier> findByPhoneNo(String phoneNo);
}