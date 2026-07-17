package com.example.demo.model;

import java.time.LocalDateTime;

public class Audit {

    private String auditId;
    private String proposalId;
    private String policyNumber;
    private String action;
    private LocalDateTime timestamp;

    // Default Constructor
    public Audit() {
    }

    // Parameterized Constructor
    public Audit(String auditId,
                 String proposalId,
                 String policyNumber,
                 String action,
                 LocalDateTime timestamp) {

        this.auditId = auditId;
        this.proposalId = proposalId;
        this.policyNumber = policyNumber;
        this.action = action;
        this.timestamp = timestamp;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}