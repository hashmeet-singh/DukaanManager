package com.rrdlabs.dukaanmanager.repositories;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameIgnoreCase(String name);

    List<Product> findByProductCategory(ProductCategory productCategory);
    
    List<Product> findByProductBrand_Id(Long id);

}
