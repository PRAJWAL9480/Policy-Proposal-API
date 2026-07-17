package com.example.demo.controller;

import com.example.demo.model.Audit;
import com.example.demo.service.AuditService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/audits")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public Collection<Audit> getAllAudits() {
        return auditService.getAllAudits();
    }
}