package com.sosesib.backend.controllers;

import com.sosesib.backend.models.entities.TimeSeries;
import com.sosesib.backend.models.requests.TimeSeriesRequestWithAggregations;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.models.responses.SOSResponse;
import com.sosesib.backend.models.responses.TimeSeriesResponseWithAggregations;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=TS_Sens1_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<TimeSeries> dummy = timeSeriesService.getAllSeriesBySensorId(1);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"measurementId", "sensorId", "measurementDate", "measurementValue"};
        String[] nameMapping = {"id", "email", "fullName", "roles", "enabled"};

        csvWriter.writeHeader(csvHeader);

        for (TimeSeries ts : dummy) {
            csvWriter.write(ts, csvHeader);
        }

        csvWriter.close();

    }

}
