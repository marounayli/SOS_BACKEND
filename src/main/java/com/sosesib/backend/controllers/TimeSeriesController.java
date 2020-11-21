package com.sosesib.backend.controllers;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.SOSResponse;
import com.sosesib.backend.models.entities.TimeSeries;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
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
    public SOSResponse<List<TimeSeries>> getTimeSeriesBySensorId(@PathVariable Integer Id){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(timeSeriesService.getBySensorId(Id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/sum/sensId/{Id}/{size}")
    public SOSResponse<List<Aggregation<Double>>> getAggregationSum(@PathVariable Integer Id, @PathVariable Integer size){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(timeSeriesService.aggregationSum(size,Id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/max/sensId/{Id}/{size}")
    public SOSResponse<List<Aggregation<Double>>> getAggregationMax(@PathVariable Integer Id, @PathVariable Integer size){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(timeSeriesService.aggregationMax(size,Id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/min/sensId/{Id}/{size}")
    public SOSResponse<List<Aggregation<Double>>> getAggregationMin(@PathVariable Integer Id, @PathVariable Integer size){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(timeSeriesService.aggregationMin(size,Id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/avg/sensId/{Id}/{size}")
    public SOSResponse<List<Aggregation<Double>>> getAggregationAvg(@PathVariable Integer Id, @PathVariable Integer size){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(timeSeriesService.aggregationAvg(size,Id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/range/sensId/{Id}/{size}")
    public SOSResponse<List<Aggregation<Double>>> getAggregationRange(@PathVariable Integer Id, @PathVariable Integer size){
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(timeSeriesService.aggregationRange(size,Id));
    }
}
