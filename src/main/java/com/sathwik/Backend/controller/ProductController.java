package com.sathwik.Backend.controller;

import com.sathwik.Backend.model.Product;
import com.sathwik.Backend.service.ProductService;
import com.sathwik.Backend.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.createProduct(product));
        }catch (IllegalArgumentException ie){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(ie.getMessage(), "Invalid data provided for the product"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, product));
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(re.getMessage(), "No product found with the provided ID"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sku,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.listProducts(name, sku, page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteProduct(@PathVariable Long id) {
        try {
            productService.softDeleteProduct(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(re.getMessage(), "No product found with the provided ID"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }
}
