package com.example.demo.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Audit;

@Repository
public class AuditRepository {

    private final ConcurrentHashMap<String, Audit> audits = new ConcurrentHashMap<>();

    public Audit save(Audit audit) {
        audits.put(audit.getAuditId(), audit);
        return audit;
    }

    public Collection<Audit> findAll() {
        return audits.values();
    }
}