package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {

    private final ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<>();

    public Customer save(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
        return customer;
    }

    public Customer findById(String customerId) {
        return customers.get(customerId);
    }

    public Collection<Customer> findAll() {
        return customers.values();
    }

    public Customer update(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
        return customer;
    }
}