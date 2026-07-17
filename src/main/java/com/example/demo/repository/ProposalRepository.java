package com.example.demo.repository;

import com.example.demo.model.Proposal;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProposalRepository {

    private final ConcurrentHashMap<String, Proposal> proposals =
            new ConcurrentHashMap<>();

    public Proposal save(Proposal proposal) {
        proposals.put(proposal.getProposalId(), proposal);
        return proposal;
    }

    public Proposal findById(String proposalId) {
        return proposals.get(proposalId);
    }

    public Collection<Proposal> findAll() {
        return proposals.values();
    }

    public Proposal update(Proposal proposal) {
        proposals.put(proposal.getProposalId(), proposal);
        return proposal;
    }

}