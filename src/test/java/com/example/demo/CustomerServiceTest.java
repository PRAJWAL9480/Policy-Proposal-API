package com.example.demo;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CustomerRepository();
        service = new CustomerService(repository);
    }

    private CustomerRequest getValidCustomerRequest() {
        CustomerRequest request = new CustomerRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setAge(25);
        request.setPanNumber("ABCDE1234F");
        request.setEmail("john@test.com");
        request.setMobileNumber("9876543210");
        request.setAddress("Bangalore");
        return request;
    }

    @Test
    void shouldCreateCustomerSuccessfully() {

        CustomerRequest request = getValidCustomerRequest();

        CustomerResponse response = service.createCustomer(request);

        assertNotNull(response);
        assertNotNull(response.getCustomerId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals(25, response.getAge());
    }

    @Test
    void shouldRejectCustomerAgeBelow18() {

        CustomerRequest request = getValidCustomerRequest();
        request.setAge(17);

        assertThrows(
                BusinessValidationException.class,
                () -> service.createCustomer(request)
        );
    }

    @Test
    void shouldRejectCustomerAgeAbove65() {

        CustomerRequest request = getValidCustomerRequest();
        request.setAge(70);

        assertThrows(
                BusinessValidationException.class,
                () -> service.createCustomer(request)
        );
    }

    @Test
    void shouldReturnCustomerById() {

        CustomerRequest request = getValidCustomerRequest();

        CustomerResponse created = service.createCustomer(request);

        CustomerResponse fetched =
                service.getCustomerById(created.getCustomerId());

        assertEquals(created.getCustomerId(), fetched.getCustomerId());
        assertEquals("John", fetched.getFirstName());
    }

    @Test
    void shouldThrowWhenCustomerNotFound() {

        assertThrows(
                CustomerNotFoundException.class,
                () -> service.getCustomerById("CUS9999")
        );
    }

    @Test
    void shouldReturnAllCustomers() {

        service.createCustomer(getValidCustomerRequest());

        CustomerRequest second = getValidCustomerRequest();
        second.setFirstName("Alice");
        second.setEmail("alice@test.com");

        service.createCustomer(second);

        List<CustomerResponse> customers =
                service.getAllCustomers();

        assertEquals(2, customers.size());
    }
}
