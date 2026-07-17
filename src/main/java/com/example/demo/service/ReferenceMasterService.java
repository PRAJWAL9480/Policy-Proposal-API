package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReferenceMasterService {

    public List<String> getReferenceData(String category) {

        switch (category.toUpperCase()) {

            case "POLICY_TERM":
                return List.of(
                        "10",
                        "15",
                        "20",
                        "25",
                        "30"
                );

            case "PAYMENT_FREQUENCY":
                return List.of(
                        "MONTHLY",
                        "QUARTERLY",
                        "HALF_YEARLY",
                        "YEARLY"
                );

            default:
                throw new IllegalArgumentException(
                        "Invalid Reference Category : " + category);
        }
    }
}