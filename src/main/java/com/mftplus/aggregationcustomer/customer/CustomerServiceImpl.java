package com.mftplus.aggregationcustomer.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);

    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

//    @Override
//    public Page<Customer> findByOrderByLastNameAsc(Pageable pageable) {
//        return customerRepository.findAll(pageable);
//    }

//    @Override
//    public Page<Customer> findByOrderByFirstNameAsc(Pageable pageable) {
//        return null;
//    }
}
