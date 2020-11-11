package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;

import java.util.List;

public interface ProductService {
    List<ProductCategory> getAllCategories();

    ProductCategory getCategory(Long categoryId);

    List<ProductCategory> getMatchingCategories(String description);

    List<ProductCategory> getCategoryByDescription(String description);

    ProductCategory createCategory(ProductCategory productCategory);

    List<ProductBrand> getAllBrands();

    ProductBrand getBrand(Long brandId);

    List<ProductBrand> getMatchingBrands(String name);

    List<ProductBrand> getBrandsByName(String name);

    ProductBrand createBrand(ProductBrand productBrand);

    List<Product> getAllProducts();

    List<Product> getProductByName(String name);

    Product getProduct(Long productId);

    Product createProduct(Long categoryId, Long brandId, Product product);
}
