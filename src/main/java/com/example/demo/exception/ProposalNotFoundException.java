package com.example.demo.exception;

public class ProposalNotFoundException extends RuntimeException {

    public ProposalNotFoundException(String message) {
        super(message);
    }

}