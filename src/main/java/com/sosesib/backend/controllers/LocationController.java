package com.sosesib.backend.controllers;

import com.sosesib.backend.models.Location;
import com.sosesib.backend.models.SOSResponse;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.services.LocationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/all")
    public SOSResponse<List<Location>> getAllLocations(){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(locationService.getAllLocations());
    }
}
