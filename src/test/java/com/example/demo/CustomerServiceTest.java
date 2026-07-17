package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

public class CustomerServiceTest {

    @Test
    void shouldRejectCustomerAgeBelow18() {

        CustomerRepository repository = new CustomerRepository();
        CustomerService service = new CustomerService(repository);

        CustomerRequest request = new CustomerRequest();

        request.setFirstName("John");
        request.setLastName("Doe");
        request.setAge(17);
        request.setPanNumber("ABCDE1234F");
        request.setEmail("john@test.com");
        request.setMobileNumber("9876543210");
        request.setAddress("Bangalore");

        assertThrows(
                BusinessValidationException.class,
                () -> service.createCustomer(request)
        );
    }
}