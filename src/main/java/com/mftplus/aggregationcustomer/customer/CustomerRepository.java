package com.mftplus.aggregationcustomer.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findByOrderByFirstNameAsc(Pageable pageable);

    Page<Customer> findByOrderByLastNameAsc(Pageable pageable);

}
