package com.sosesib.backend.controllers;

import com.sosesib.backend.models.entities.Measurement;
import com.sosesib.backend.models.responses.SOSResponse;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.services.MeasurementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    SOSResponse<List<Measurement>> getAllMeasurements(){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(measurementService.getAllMeasurements());
    }
}
