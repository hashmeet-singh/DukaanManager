package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;
import com.rrdlabs.dukaanmanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/categories/all")
    public List<ProductCategory> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/categories")
    public List<ProductCategory> getMatchingCategories(@RequestParam String description) {
        return productService.getMatchingCategories(description);
    }

    @PostMapping("/categories")
    public ProductCategory createCategory(@RequestBody ProductCategory productCategory) {
        return productService.createCategory(productCategory);
    }

    @GetMapping("/brands/all")
    public List<ProductBrand> getAllBrands() {
        return productService.getAllBrands();
    }

    @GetMapping("/brands")
    public List<ProductBrand> getMatchingBrands(@RequestParam String name) {
        return productService.getMatchingBrands(name);
    }

    @PostMapping("/brands")
    public ProductBrand createBrand(@RequestBody ProductBrand productBrand) {
        return productService.createBrand(productBrand);
    }

    @PostMapping("/{categoryId}/{brandId}")
    public Product addProduct(@PathVariable int categoryId, @PathVariable int brandId, @RequestBody Product product) {
        return productService.createProduct(categoryId, brandId, product);
    }
}
