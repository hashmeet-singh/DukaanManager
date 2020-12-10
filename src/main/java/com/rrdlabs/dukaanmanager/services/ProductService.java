package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;

import java.util.List;

public interface ProductService {
    //    --------------------------------------------------------------------------------------------
    //    Product Category
    //    --------------------------------------------------------------------------------------------
    List<ProductCategory> getAllCategories();

    ProductCategory getCategory(Long categoryId);

    List<ProductCategory> getMatchingCategories(String description);

    List<ProductCategory> getCategoryByDescription(String description);

    ProductCategory createCategory(ProductCategory productCategory);

    ProductCategory updateCategory(ProductCategory productCategory);

    void deleteCategory(Long categoryId);

    List<Product> categoryProducts(Long categoryId);

    //    --------------------------------------------------------------------------------------------
    //    Product Brand
    //    --------------------------------------------------------------------------------------------

    List<ProductBrand> getAllBrands();

    ProductBrand getBrand(Long brandId);

    List<ProductBrand> getMatchingBrands(String name);

    List<ProductBrand> getBrandsByName(String name);

    ProductBrand createBrand(ProductBrand productBrand);

    ProductBrand updateBrand(ProductBrand productBrand);

    void deleteBrand(Long brandId);

    List<Product> brandProducts(Long brandId);

    //    --------------------------------------------------------------------------------------------
    //    Product Brand
    //    --------------------------------------------------------------------------------------------

    List<Product> getAllProducts();

    List<Product> getProductByName(String name);

    Product getProduct(Long productId);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
