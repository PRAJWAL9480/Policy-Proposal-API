package com.example.demo.dto;

import com.example.demo.model.PaymentFrequency;
import com.example.demo.model.ProposalStatus;

public class ProposalResponse {

    private String proposalId;
    private String customerId;
    private Integer policyTerm;
    private Double sumAssured;
    private Double annualPremium;
    private PaymentFrequency paymentFrequency;
    private String nomineeName;
    private ProposalStatus status;
    private String policyNumber;

    public ProposalResponse() {
    }

    public ProposalResponse(String proposalId,
                            String customerId,
                            Integer policyTerm,
                            Double sumAssured,
                            Double annualPremium,
                            PaymentFrequency paymentFrequency,
                            String nomineeName,
                            ProposalStatus status,
                            String policyNumber) {

        this.proposalId = proposalId;
        this.customerId = customerId;
        this.policyTerm = policyTerm;
        this.sumAssured = sumAssured;
        this.annualPremium = annualPremium;
        this.paymentFrequency = paymentFrequency;
        this.nomineeName = nomineeName;
        this.status = status;
        this.policyNumber = policyNumber;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getPolicyTerm() {
        return policyTerm;
    }

    public void setPolicyTerm(Integer policyTerm) {
        this.policyTerm = policyTerm;
    }

    public Double getSumAssured() {
        return sumAssured;
    }

    public void setSumAssured(Double sumAssured) {
        this.sumAssured = sumAssured;
    }

    public Double getAnnualPremium() {
        return annualPremium;
    }

    public void setAnnualPremium(Double annualPremium) {
        this.annualPremium = annualPremium;
    }

    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
}