package com.example.demo.service;

import com.example.demo.dto.ProposalRequest;
import com.example.demo.dto.ProposalResponse;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.ProposalNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProposalRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final CustomerRepository customerRepository;
    private final AuditService auditService;

    private final AtomicInteger proposalCounter = new AtomicInteger(1000);
    private final AtomicInteger policyCounter = new AtomicInteger(100000);

    public ProposalService(ProposalRepository proposalRepository,
                           CustomerRepository customerRepository,
                           AuditService auditService) {

        this.proposalRepository = proposalRepository;
        this.customerRepository = customerRepository;
        this.auditService = auditService;
    }

    public ProposalResponse createProposal(ProposalRequest request) {

        Customer customer = validateCustomer(request.getCustomerId());

        validatePolicyTerm(request.getPolicyTerm());
        validateSumAssured(request.getSumAssured());
        validatePremium(request.getAnnualPremium());
        validatePan(customer, request.getAnnualPremium());
        validateNominee(customer, request.getNomineeName());
        validatePaymentFrequency(request.getPaymentFrequency());

        Proposal proposal = new Proposal();

        proposal.setProposalId(generateProposalId());
        proposal.setCustomerId(request.getCustomerId());
        proposal.setPolicyTerm(request.getPolicyTerm());
        proposal.setSumAssured(request.getSumAssured());
        proposal.setAnnualPremium(request.getAnnualPremium());
        proposal.setPaymentFrequency(request.getPaymentFrequency());
        proposal.setNomineeName(request.getNomineeName());
        proposal.setStatus(ProposalStatus.DRAFT);

        proposalRepository.save(proposal);

        return convertToResponse(proposal);
    }

    public ProposalResponse getProposalById(String proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null) {
            throw new ProposalNotFoundException("Proposal not found.");
        }

        return convertToResponse(proposal);
    }

    public ProposalResponse submitProposal(String proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null) {
            throw new ProposalNotFoundException("Proposal not found.");
        }

        if (proposal.getStatus() == ProposalStatus.SUBMITTED) {
            throw new BusinessValidationException("Proposal already submitted.");
        }

        proposal.setPolicyNumber(generatePolicyNumber());
        proposal.setStatus(ProposalStatus.SUBMITTED);

        proposalRepository.update(proposal);

        auditService.createAudit(
                proposal.getProposalId(),
                proposal.getPolicyNumber());

        return convertToResponse(proposal);
    }

    private Customer validateCustomer(String customerId) {

        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer does not exist.");
        }

        return customer;
    }

    private void validatePolicyTerm(Integer term) {

        if (!Set.of(10, 15, 20, 25, 30).contains(term)) {
            throw new BusinessValidationException("Invalid Policy Term");
        }
    }

    private void validateSumAssured(Double amount) {

        if (amount < 100000 || amount > 50000000) {
            throw new BusinessValidationException(
                    "Sum Assured should be between 1 Lakh and 5 Crore");
        }
    }

    private void validatePremium(Double premium) {

        if (premium < 5000) {
            throw new BusinessValidationException(
                    "Minimum Premium is ₹5000");
        }
    }

    private void validatePan(Customer customer, Double premium) {

        if (premium > 50000 &&
                (customer.getPanNumber() == null ||
                 customer.getPanNumber().isBlank())) {

            throw new BusinessValidationException(
                    "PAN is mandatory.");
        }
    }

    private void validateNominee(Customer customer,
                                 String nominee) {

        if (nominee == null || nominee.isBlank()) {
            throw new BusinessValidationException(
                    "Nominee required.");
        }

        String customerName =
                customer.getFirstName() + " " + customer.getLastName();

        if (customerName.equalsIgnoreCase(nominee)) {
            throw new BusinessValidationException(
                    "Nominee cannot be customer.");
        }
    }

    private void validatePaymentFrequency(
            PaymentFrequency paymentFrequency) {

        if (paymentFrequency == null) {
            throw new BusinessValidationException(
                    "Payment Frequency Required");
        }
    }

    private String generateProposalId() {
        return "PROP" + proposalCounter.incrementAndGet();
    }

    private String generatePolicyNumber() {
        return "POL" + policyCounter.incrementAndGet();
    }

    private ProposalResponse convertToResponse(Proposal proposal) {

        ProposalResponse response = new ProposalResponse();

        response.setProposalId(proposal.getProposalId());
        response.setCustomerId(proposal.getCustomerId());
        response.setPolicyTerm(proposal.getPolicyTerm());
        response.setSumAssured(proposal.getSumAssured());
        response.setAnnualPremium(proposal.getAnnualPremium());
        response.setPaymentFrequency(proposal.getPaymentFrequency());
        response.setNomineeName(proposal.getNomineeName());
        response.setStatus(proposal.getStatus());
        response.setPolicyNumber(proposal.getPolicyNumber());

        return response;
    }
}