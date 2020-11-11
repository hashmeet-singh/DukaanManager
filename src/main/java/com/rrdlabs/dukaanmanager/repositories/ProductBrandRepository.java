package com.rrdlabs.dukaanmanager.repositories;

import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {

    List<ProductBrand> findByBrandNameContainingIgnoreCase(String name);

    List<ProductBrand> findByBrandNameIgnoreCase(String name);
}
