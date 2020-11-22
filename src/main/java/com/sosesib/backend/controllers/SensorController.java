package com.sosesib.backend.controllers;

import com.sosesib.backend.models.responses.SOSResponse;
import com.sosesib.backend.models.entities.Sensor;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.services.SensorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value="/id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public SOSResponse<Sensor> getSensorById(@PathVariable Integer id){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(sensorService.getSensorById(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value="/region/{region}",produces = MediaType.APPLICATION_JSON_VALUE)
    public SOSResponse<List<Sensor>> findSensorByRegion(@PathVariable String region){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(sensorService.findSensorByRegion(region));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value="/city/{city}",produces = MediaType.APPLICATION_JSON_VALUE)
    public SOSResponse<List<Sensor>> findSensorByCity(@PathVariable String city){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(sensorService.findSensorByCity(city));
    }
}
