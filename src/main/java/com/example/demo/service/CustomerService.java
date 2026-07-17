package com.example.demo.service;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AtomicInteger customerCounter = new AtomicInteger(1000);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {

        validateAge(request.getAge());

        Customer customer = new Customer();

        customer.setCustomerId(generateCustomerId());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setAge(request.getAge());
        customer.setPanNumber(request.getPanNumber());
        customer.setEmail(request.getEmail());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setAddress(request.getAddress());

        customerRepository.save(customer);

        return convertToResponse(customer);
    }

    public List<CustomerResponse> getAllCustomers() {

        Collection<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> responses = new ArrayList<>();

        for (Customer customer : customers) {
            responses.add(convertToResponse(customer));
        }

        return responses;
    }

    public CustomerResponse getCustomerById(String customerId) {

        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }

        return convertToResponse(customer);
    }

    public CustomerResponse updateCustomer(String customerId,
                                           CustomerRequest request) {

        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }

        validateAge(request.getAge());

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setAge(request.getAge());
        customer.setPanNumber(request.getPanNumber());
        customer.setEmail(request.getEmail());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setAddress(request.getAddress());

        customerRepository.update(customer);

        return convertToResponse(customer);
    }

    private void validateAge(Integer age) {

        if (age == null || age < 18 || age > 65) {
            throw new BusinessValidationException(
                    "Customer age must be between 18 and 65");
        }
    }

    private String generateCustomerId() {
        return "CUS" + customerCounter.incrementAndGet();
    }

    private CustomerResponse convertToResponse(Customer customer) {

        CustomerResponse response = new CustomerResponse();

        response.setCustomerId(customer.getCustomerId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setAge(customer.getAge());
        response.setEmail(customer.getEmail());
        response.setMobileNumber(customer.getMobileNumber());
        response.setAddress(customer.getAddress());

        return response;
    }
}