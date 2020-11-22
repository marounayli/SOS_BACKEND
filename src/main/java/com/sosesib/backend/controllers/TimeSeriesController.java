package com.sosesib.backend.controllers;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.responses.SOSResponse;
import com.sosesib.backend.models.requests.TimeSeriesRequestWithAggregations;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.models.responses.TimeSeriesResponseWithAggregations;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ts")
public class TimeSeriesController {

    private final TimeSeriesService timeSeriesService;

    public TimeSeriesController(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/aggregations")
    public SOSResponse<TimeSeriesResponseWithAggregations> getAggregations(@RequestBody TimeSeriesRequestWithAggregations request){
        TimeSeriesResponseWithAggregations response = new TimeSeriesResponseWithAggregations();
        response.setAggregationMap(timeSeriesService.getAggregations(request));
        response.setOriginal(timeSeriesService.getTimeSeries(request));
        return SOSResponseGenerator.GenerateSuccessfulQueryResponse(response);
    }

    @GetMapping("/date")
    public LocalDateTime getLocalDateTime(){
        return LocalDateTime.now();
    }
}
