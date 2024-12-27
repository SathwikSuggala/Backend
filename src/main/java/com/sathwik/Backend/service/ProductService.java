package com.sathwik.Backend.service;

import com.sathwik.Backend.model.Product;
import com.sathwik.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        if(!existingProduct.getSku().equals(updatedProduct.getSku())){
            if(productRepository.findBySku(updatedProduct.getSku()).isPresent()){
                throw new IllegalArgumentException("Product already exists with given sku");
            }
        }
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setSku(updatedProduct.getSku());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        return productRepository.save(existingProduct);
    }

    public List<Product> listProducts(String name, String sku, int page, int size) {
        if (name != null) {
            return productRepository.findByNameContaining(name, PageRequest.of(page, size)).getContent();
        }
        if (sku != null) {
            return productRepository.findBySkuContaining(sku, PageRequest.of(page, size)).getContent();
        }
        return productRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setIsActive(false);
        productRepository.save(product);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Valid price is required");
        }
        if (product.getQuantity() == null || product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if(productRepository.findBySku(product.getSku()).isPresent()){
            throw new IllegalArgumentException(("Product already exists"));
        }
    }
}
