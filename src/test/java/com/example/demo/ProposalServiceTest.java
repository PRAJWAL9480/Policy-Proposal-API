package com.example.demo;

import com.example.demo.dto.ProposalRequest;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.model.Customer;
import com.example.demo.model.PaymentFrequency;
import com.example.demo.repository.AuditRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProposalRepository;
import com.example.demo.service.AuditService;
import com.example.demo.service.ProposalService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProposalServiceTest {

    @Test
    void shouldRejectInvalidPolicyTerm() {

        ProposalRepository proposalRepository = new ProposalRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        AuditRepository auditRepository = new AuditRepository();

        AuditService auditService =
                new AuditService(auditRepository);

        ProposalService proposalService =
                new ProposalService(
                        proposalRepository,
                        customerRepository,
                        auditService);

        Customer customer = new Customer();

        customer.setCustomerId("CUS1001");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setAge(25);
        customer.setPanNumber("ABCDE1234F");

        customerRepository.save(customer);

        ProposalRequest request = new ProposalRequest();

        request.setCustomerId("CUS1001");
        request.setPolicyTerm(12);
        request.setSumAssured(1000000.0);
        request.setAnnualPremium(10000.0);
        request.setPaymentFrequency(PaymentFrequency.YEARLY);
        request.setNomineeName("Rahul");

        assertThrows(
                BusinessValidationException.class,
                () -> proposalService.createProposal(request)
        );
    }
}