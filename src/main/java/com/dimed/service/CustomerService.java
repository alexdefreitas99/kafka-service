package com.dimed.service;

import com.dimed.model.Customer;
import com.dimed.repository.CustomerRepository;
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
