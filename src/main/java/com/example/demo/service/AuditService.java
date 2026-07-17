package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.demo.model.Audit;
import com.example.demo.repository.AuditRepository;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    private final AtomicInteger auditCounter = new AtomicInteger(1000);

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void createAudit(String proposalId, String policyNumber) {

        Audit audit = new Audit();

        audit.setAuditId("AUD" + auditCounter.incrementAndGet());
        audit.setProposalId(proposalId);
        audit.setPolicyNumber(policyNumber);
        audit.setAction("Proposal Submitted");
        audit.setTimestamp(LocalDateTime.now());

        auditRepository.save(audit);
    }

    public Collection<Audit> getAllAudits() {
        return auditRepository.findAll();
    }
}