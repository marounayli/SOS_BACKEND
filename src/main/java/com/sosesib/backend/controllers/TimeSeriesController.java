package com.sosesib.backend.controllers;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.TimeSeries;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ts")
public class TimeSeriesController {

    private final TimeSeriesService timeSeriesService;

    public TimeSeriesController(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }

    @GetMapping("")
    public String Hello(){
        return "Hello";
    }

    @GetMapping("/sensId/{Id}")
    public List<TimeSeries> getTimeSeriesBySensorId(@PathVariable Integer Id){
        return timeSeriesService.getBySensorId(Id);
    }

    @GetMapping("/sensId/{Id}/{size}")
    public List<Aggregation> getAggregationBySizeAndSensorId(@PathVariable Integer Id, @PathVariable Integer size){
        return timeSeriesService.aggregationSum(size,Id);

    }
}
