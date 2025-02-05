package com.mftplus.aggregationcustomer.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Page<Product> getAllProducts(Pageable pageable);
}
