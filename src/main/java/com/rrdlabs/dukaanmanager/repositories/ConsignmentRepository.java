package com.rrdlabs.dukaanmanager.repositories;

import com.rrdlabs.dukaanmanager.entities.Consignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignmentRepository extends JpaRepository<Consignment, Long> {
}
