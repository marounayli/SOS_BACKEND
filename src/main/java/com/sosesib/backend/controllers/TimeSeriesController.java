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

        @GetMapping("/sum/sensId/{Id}/{size}")
    public List<Aggregation<Double>> getAggregationSum(@PathVariable Integer Id, @PathVariable Integer size){
        return timeSeriesService.aggregationSum(size,Id);
    }

    @GetMapping("/prod/sensId/{Id}/{size}")
    public List<Aggregation<Double>> getAggregationProd(@PathVariable Integer Id, @PathVariable Integer size){
        return timeSeriesService.aggregationProd(size,Id);
    }
}
