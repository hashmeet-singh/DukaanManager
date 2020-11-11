package com.rrdlabs.dukaanmanager.repositories;

import com.rrdlabs.dukaanmanager.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findByPhoneNo(String phoneNo);

    List<Staff> findByEmail(String email);
}
