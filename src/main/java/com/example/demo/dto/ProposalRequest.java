package com.example.demo.dto;

import com.example.demo.model.PaymentFrequency;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProposalRequest {

    @NotBlank
    private String customerId;

    @NotNull
    private Integer policyTerm;

    @NotNull
    private Double sumAssured;

    @NotNull
    private Double annualPremium;

    @NotNull
    private PaymentFrequency paymentFrequency;

    @NotBlank
    private String nomineeName;

    public ProposalRequest() {
    }

    public ProposalRequest(String customerId,
                           Integer policyTerm,
                           Double sumAssured,
                           Double annualPremium,
                           PaymentFrequency paymentFrequency,
                           String nomineeName) {
        this.customerId = customerId;
        this.policyTerm = policyTerm;
        this.sumAssured = sumAssured;
        this.annualPremium = annualPremium;
        this.paymentFrequency = paymentFrequency;
        this.nomineeName = nomineeName;
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
}