package com.sosesib.backend.controllers;


import com.sosesib.backend.models.entities.Specification;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.models.responses.SOSResponse;
import com.sosesib.backend.services.SpecificationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
    private final SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public SOSResponse<List<Specification>> getAllLocations(){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(specificationService.getAllSpecifications());
    }
}
