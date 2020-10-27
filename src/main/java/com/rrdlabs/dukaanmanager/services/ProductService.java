package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.ProductBrand;
import com.rrdlabs.dukaanmanager.entities.ProductCategory;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.ProductBrandRepository;
import com.rrdlabs.dukaanmanager.repositories.ProductCategoryRepository;
import com.rrdlabs.dukaanmanager.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductBrandRepository brandRepository;

    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<ProductCategory> getMatchingCategories(String description) {
        return categoryRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<ProductCategory> getrecordsWithDescription(String description) {
        return categoryRepository.findByDescriptionIgnoreCase(description);
    }

    public ProductCategory addCategory(ProductCategory productCategory) {
        if (!getrecordsWithDescription(productCategory.getDescription()).isEmpty())
            throw new KeyColumnDuplicationException("Category : '" + productCategory.getDescription() + "' is already present");
        productCategory.setId(0);

        return categoryRepository.save(productCategory);
    }

    public List<ProductBrand> getAllBrands() {
        return brandRepository.findAll();
    }

    public List<ProductBrand> getMatchingBrands(String name) {
        return brandRepository.findByBrandNameContainingIgnoreCase(name);
    }

    public List<ProductBrand> getrecordsWithBrandName(String name) {
        return brandRepository.findByBrandNameIgnoreCase(name);
    }

    public ProductBrand addBrand(ProductBrand productBrand) {
        if (!getrecordsWithBrandName(productBrand.getBrandName()).isEmpty())
            throw new KeyColumnDuplicationException("Brand : '" + productBrand.getBrandName() + "' is already present");
        productBrand.setId(0);

        return brandRepository.save(productBrand);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name);
    }

    public Product addProduct(int categoryId, int brandId, Product product) {
        ProductCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException("No category with id: " + categoryId + " found."));
        ProductBrand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RecordNotFoundException("No brand with id: " + brandId + " found."));

        if (!getProductByName(product.getName()).isEmpty())
            throw new KeyColumnDuplicationException("Product : '" + product.getName() + "' is already present");
        product.setId(0);
        product.setProductCategory(category);
        product.setProductBrand(brand);
        return productRepository.save(product);

    }
}
