package com.mftplus.aggregationcustomer;

import com.mftplus.aggregationcustomer.product.Product;
import com.mftplus.aggregationcustomer.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//@Slf4j
public class ProductDataInitialize {    //implements CommandLineRunner

//    private final ProductService productService;
//    private final ProductFakeDataService productFakeDataService;
//
//    public ProductDataInitialize(ProductService productService, ProductFakeDataService productFakeDataService) {
//        this.productService = productService;
//        this.productFakeDataService = productFakeDataService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        int n = 25;
//        for (int i = 1; i <= n; i++) {
//            Product product = productFakeDataService.createFakeProduct();
//            productService.saveProduct(product);
//        }
//        log.info("{} Products Created",n);
//    }
}
