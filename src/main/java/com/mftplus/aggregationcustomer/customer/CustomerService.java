package com.mftplus.aggregationcustomer.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    Page<Customer> getAllCustomers(Pageable pageable);

    //Page<Customer> findByOrderByLastNameAsc(Pageable pageable);
    //Page<Customer> findByOrderByFirstNameAsc(Pageable pageable);
}