package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;
import com.rrdlabs.dukaanmanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/{categoryId}/{brandId}/add")
    public Product addProduct(@PathVariable int categoryId, @PathVariable int brandId, @RequestBody Product product) {
        Product createdProduct = productService.addProduct(categoryId, brandId, product);
        return createdProduct;
    }

    @GetMapping("/categories/all")
    public List<ProductCategory> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/categories")
    public List<ProductCategory> getMatchingCategories(@RequestParam String description) {
        return productService.getMatchingCategories(description);
    }

    @PostMapping("/categories/add")
    public ProductCategory createCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory createdCategory = productService.addCategory(productCategory);
        return createdCategory;
    }

    @GetMapping("/brands/all")
    public List<ProductBrand> getAllBrands() {
        return productService.getAllBrands();
    }

    @GetMapping("/brands")
    public List<ProductBrand> getMatchingBrands(@RequestParam String name) {
        return productService.getMatchingBrands(name);
    }

    @PostMapping("/brands/add")
    public ProductBrand createBrand(@RequestBody ProductBrand productBrand) {
        ProductBrand createdBrand = productService.addBrand(productBrand);
        return createdBrand;
    }
}
