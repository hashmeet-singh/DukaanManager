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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductBrandRepository brandRepository;

    @Override
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ProductCategory getCategory(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Category Id: " + categoryId));
    }

    @Override
    public List<ProductCategory> getMatchingCategories(String description) {
        return categoryRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @Override
    public List<ProductCategory> getCategoryByDescription(String description) {
        return categoryRepository.findByDescriptionIgnoreCase(description);
    }

    @Override
    public ProductCategory createCategory(ProductCategory productCategory) {
        if (!getCategoryByDescription(productCategory.getDescription()).isEmpty())
            throw new KeyColumnDuplicationException("Category : '" + productCategory.getDescription() + "' is already present");
        productCategory.setId(0L);

        return categoryRepository.save(productCategory);
    }

    @Override
    public List<ProductBrand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public ProductBrand getBrand(Long brandId) {
        return brandRepository
                .findById(brandId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Brand Id: " + brandId));
    }

    @Override
    public List<ProductBrand> getMatchingBrands(String name) {
        return brandRepository.findByBrandNameContainingIgnoreCase(name);
    }

    @Override
    public List<ProductBrand> getBrandsByName(String name) {
        return brandRepository.findByBrandNameIgnoreCase(name);
    }

    @Override
    public ProductBrand createBrand(ProductBrand productBrand) {
        if (!getBrandsByName(productBrand.getBrandName()).isEmpty())
            throw new KeyColumnDuplicationException("Brand : '" + productBrand.getBrandName() + "' is already present");
        productBrand.setId(0L);

        return brandRepository.save(productBrand);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Product createProduct(Long categoryId, Long brandId, Product product) {
        ProductCategory category = getCategory(categoryId);
        ProductBrand brand = getBrand(brandId);

        if (!getProductByName(product.getName()).isEmpty())
            throw new KeyColumnDuplicationException("Product : '" + product.getName() + "' is already present");
        product.setId(0L);
        product.setProductCategory(category);
        product.setProductBrand(brand);
        return productRepository.save(product);

    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Product Id: " + productId));
    }
}
