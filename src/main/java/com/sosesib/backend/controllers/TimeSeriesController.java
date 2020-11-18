package com.sosesib.backend.controllers;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.TimeSeries;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ts")
public class TimeSeriesController {

    private final TimeSeriesService timeSeriesService;

    public TimeSeriesController(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("")
    public String Hello(){
        return "Hello";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/sensId/{Id}")
    public List<TimeSeries> getTimeSeriesBySensorId(@PathVariable Integer Id){
        return timeSeriesService.getBySensorId(Id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/sum/sensId/{Id}/{size}")
    public List<Aggregation<Double>> getAggregationSum(@PathVariable Integer Id, @PathVariable Integer size){
        return timeSeriesService.aggregationSum(size,Id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/prod/sensId/{Id}/{size}")
    public List<Aggregation<Double>> getAggregationProd(@PathVariable Integer Id, @PathVariable Integer size){
        return timeSeriesService.aggregationProd(size,Id);
    }
}
