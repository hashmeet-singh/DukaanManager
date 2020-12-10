package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;
import com.rrdlabs.dukaanmanager.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    --------------------------------------------------------------------------------------------
    //    Product Category APIs
    //    --------------------------------------------------------------------------------------------

    @GetMapping("/categories")
    public List<ProductCategory> getAllCategories(@RequestParam Optional<String> description) {
        if (description.isPresent()) {
            return productService.getMatchingCategories(description.get());
        }

        return productService.getAllCategories();
    }

    @GetMapping("/categories/{category_id}")
    public ProductCategory getCategoryById(@PathVariable(name = "category_id") Long id) {
        return productService.getCategory(id);
    }

    @GetMapping("/categories/{category_id}/products")
    public List<Product> getCategoryProducts(@PathVariable(name = "category_id") Long id) {
        return productService.categoryProducts(id);
    }

    @PostMapping("/categories")
    public ResponseEntity<ProductCategory> createCategory(@Valid @RequestBody ProductCategory productCategory) {
        ProductCategory category = productService.createCategory(productCategory);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{category_id}")
    public ProductCategory updateCategory(@PathVariable(name = "category_id") Long id, @Valid @RequestBody ProductCategory productCategory) {
        productService.createCategory(productCategory);
        productCategory.setId(id);
        return productService.updateCategory(productCategory);
    }

    @DeleteMapping("/categories/{category_id}")
    public void deleteCategory(@PathVariable(name = "category_id") Long id) {
        productService.deleteCategory(id);
    }

    //    --------------------------------------------------------------------------------------------
    //    Product Brand APIs
    //    --------------------------------------------------------------------------------------------

    @GetMapping("/brands")
    public List<ProductBrand> getAllBrands(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return productService.getMatchingBrands(name.get());
        }
        return productService.getAllBrands();
    }

    @GetMapping("/brands/{brand_id}")
    public ProductBrand getBrandById(@PathVariable(name = "brand_id") Long id) {
        return productService.getBrand(id);
    }

    @GetMapping("/brands/{brand_id}/products")
    public List<Product> getBrandProducts(@PathVariable(name = "brand_id") Long id) {
        return productService.brandProducts(id);
    }

    @PostMapping("/brands")
    public ResponseEntity<ProductBrand> createBrand(@Valid @RequestBody ProductBrand productBrand) {
        ProductBrand brand = productService.createBrand(productBrand);
        return new ResponseEntity<>(brand, HttpStatus.CREATED);
    }

    @PutMapping("/brands/{brand_id}")
    public ProductBrand updateBrand(@PathVariable(name = "brand_id") Long id, @Valid @RequestBody ProductBrand productBrand) {
        productService.getBrand(id);
        productBrand.setId(id);
        return productService.updateBrand(productBrand);
    }

    @DeleteMapping("/brands/{brand_id}")
    public void deleteBrand(@PathVariable(name = "brand_id") Long id) {
        productService.deleteBrand(id);
    }

    //    --------------------------------------------------------------------------------------------
    //    Product Brand APIs
    //    --------------------------------------------------------------------------------------------

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{product_id}")
    public Product getProductById(@PathVariable(name = "product_id") Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/products/{product_id}")
    public Product updateProduct(@PathVariable(name = "product_id") Long id, @Valid @RequestBody Product product) {
        productService.getProduct(id);
        product.setId(id);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products/{product_id}")
    public void deleteProduct(@PathVariable(name = "product_id") Long id) {
        productService.deleteProduct(id);
    }
}
