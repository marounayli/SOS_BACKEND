package com.sosesib.backend.controllers;

import com.sosesib.backend.models.SOSResponse;
import com.sosesib.backend.models.entities.Sensor;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.services.SensorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public SOSResponse<List<Sensor>> getAllSensors(){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(sensorService.getAllSensors());
    }
}
