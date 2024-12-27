package com.sathwik.Backend.repository;

import com.sathwik.Backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // searching using product name
    Page<Product> findByNameContaining(String name, Pageable pageable);

    // searching using sku
    Page<Product> findBySkuContaining(String sku, Pageable pageable);

    Optional<Product> findBySku(String sku);

}
