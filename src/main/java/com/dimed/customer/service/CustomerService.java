package com.dimed.customer.service;

import com.dimed.customer.model.Customer;
import com.dimed.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer createCostumer(Customer customer) {
        return repository.save(customer);
    }
}
