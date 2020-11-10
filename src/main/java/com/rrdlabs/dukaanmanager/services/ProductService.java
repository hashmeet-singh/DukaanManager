package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;

import java.util.List;

public interface ProductService {
    List<ProductCategory> getAllCategories();

    List<ProductCategory> getMatchingCategories(String description);

    List<ProductCategory> getCategoryByDescription(String description);

    ProductCategory createCategory(ProductCategory productCategory);

    List<ProductBrand> getAllBrands();

    List<ProductBrand> getMatchingBrands(String name);

    List<ProductBrand> getBrandsByName(String name);

    ProductBrand createBrand(ProductBrand productBrand);

    List<Product> getAllProducts();

    List<Product> getProductByName(String name);

    Product getProduct(int productId);

    Product createProduct(int categoryId, int brandId, Product product);
}
