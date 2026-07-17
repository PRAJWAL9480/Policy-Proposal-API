package com.example.demo.controller;

import com.example.demo.dto.ProposalRequest;
import com.example.demo.dto.ProposalResponse;
import com.example.demo.service.ProposalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    public ProposalResponse createProposal(
            @Valid @RequestBody ProposalRequest request) {

        return proposalService.createProposal(request);

    }

    @GetMapping("/{id}")
    public ProposalResponse getProposal(
            @PathVariable String id) {

        return proposalService.getProposalById(id);

    }

    @PostMapping("/{id}/submit")
    public ProposalResponse submitProposal(
            @PathVariable String id) {

        return proposalService.submitProposal(id);

    }

}