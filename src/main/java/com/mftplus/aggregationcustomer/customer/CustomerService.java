package com.mftplus.aggregationcustomer.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    Page<Customer> getAllCustomers(Pageable pageable);

    List<Customer> findAll();

    //Page<Customer> findByOrderByLastNameAsc(Pageable pageable);
    //Page<Customer> findByOrderByFirstNameAsc(Pageable pageable);
}