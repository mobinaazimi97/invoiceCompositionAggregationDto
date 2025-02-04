package com.mftplus.aggregationcustomer.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product>findAll(Pageable pageable);
    Page<Product>findByOrderByNameAsc(Pageable pageable);
    Page<Product>findByOrderByBrandAsc(Pageable pageable);

}
