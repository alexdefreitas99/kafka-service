package com.dimed.customer.repository;

import com.dimed.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByName(String name);
}
