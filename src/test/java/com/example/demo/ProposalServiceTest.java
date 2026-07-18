package com.example.demo;

import com.example.demo.dto.ProposalRequest;
import com.example.demo.dto.ProposalResponse;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.ProposalNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.PaymentFrequency;
import com.example.demo.model.ProposalStatus;
import com.example.demo.repository.AuditRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProposalRepository;
import com.example.demo.service.AuditService;
import com.example.demo.service.ProposalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProposalServiceTest {

    private ProposalService proposalService;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {

        ProposalRepository proposalRepository = new ProposalRepository();
        customerRepository = new CustomerRepository();
        AuditRepository auditRepository = new AuditRepository();

        AuditService auditService =
                new AuditService(auditRepository);

        proposalService =
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
    }

    private ProposalRequest getValidRequest() {

        ProposalRequest request = new ProposalRequest();

        request.setCustomerId("CUS1001");
        request.setPolicyTerm(20);
        request.setSumAssured(1000000.0);
        request.setAnnualPremium(10000.0);
        request.setPaymentFrequency(PaymentFrequency.YEARLY);
        request.setNomineeName("Rahul");

        return request;
    }

    @Test
    void shouldCreateProposalSuccessfully() {

        ProposalResponse response =
                proposalService.createProposal(getValidRequest());

        assertNotNull(response);
        assertNotNull(response.getProposalId());
        assertEquals(ProposalStatus.DRAFT, response.getStatus());
    }

    @Test
    void shouldRejectInvalidPolicyTerm() {

        ProposalRequest request = getValidRequest();
        request.setPolicyTerm(12);

        assertThrows(
                BusinessValidationException.class,
                () -> proposalService.createProposal(request)
        );
    }

    @Test
    void shouldRejectInvalidSumAssured() {

        ProposalRequest request = getValidRequest();
        request.setSumAssured(50000.0);

        assertThrows(
                BusinessValidationException.class,
                () -> proposalService.createProposal(request)
        );
    }

    @Test
    void shouldRejectPremiumBelow5000() {

        ProposalRequest request = getValidRequest();
        request.setAnnualPremium(3000.0);

        assertThrows(
                BusinessValidationException.class,
                () -> proposalService.createProposal(request)
        );
    }

    @Test
    void shouldRejectNullPaymentFrequency() {

        ProposalRequest request = getValidRequest();
        request.setPaymentFrequency(null);

        assertThrows(
                BusinessValidationException.class,
                () -> proposalService.createProposal(request)
        );
    }

    @Test
    void shouldRejectSameNomineeAsCustomer() {

        ProposalRequest request = getValidRequest();
        request.setNomineeName("John Doe");

        assertThrows(
                BusinessValidationException.class,
                () -> proposalService.createProposal(request)
        );
    }

    @Test
    void shouldThrowWhenCustomerDoesNotExist() {

        ProposalRequest request = getValidRequest();
        request.setCustomerId("CUS9999");

        assertThrows(
                CustomerNotFoundException.class,
                () -> proposalService.createProposal(request)
        );
    }

    @Test
    void shouldSubmitProposalSuccessfully() {

        ProposalResponse proposal =
                proposalService.createProposal(getValidRequest());

        ProposalResponse submitted =
                proposalService.submitProposal(
                        proposal.getProposalId());

        assertEquals(
                ProposalStatus.SUBMITTED,
                submitted.getStatus());

        assertNotNull(submitted.getPolicyNumber());
    }

    @Test
    void shouldThrowWhenProposalNotFound() {

        assertThrows(
                ProposalNotFoundException.class,
                () -> proposalService.submitProposal("PROP9999")
        );
    }

    @Test
    void shouldRejectSubmittingProposalTwice() {

        ProposalResponse proposal =
                proposalService.createProposal(getValidRequest());

        proposalService.submitProposal(
                proposal.getProposalId());

        assertThrows(
                BusinessValidationException.class,
                () -> proposalService.submitProposal(
                        proposal.getProposalId())
        );
    }
}
