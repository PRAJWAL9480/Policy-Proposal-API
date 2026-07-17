package com.example.demo.controller;

import com.example.demo.service.ReferenceMasterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reference-master")
public class ReferenceMasterController {

    private final ReferenceMasterService referenceMasterService;

    public ReferenceMasterController(ReferenceMasterService referenceMasterService) {
        this.referenceMasterService = referenceMasterService;
    }

    @GetMapping("/{category}")
    public List<String> getReferenceMaster(
            @PathVariable String category) {

        return referenceMasterService.getReferenceData(category);
    }
}