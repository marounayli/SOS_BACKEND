package com.sosesib.backend.controllers;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.entities.TimeSeries;
import com.sosesib.backend.models.requests.TimeSeriesRequestWithAggregations;
import com.sosesib.backend.models.response.generators.SOSResponseGenerator;
import com.sosesib.backend.models.responses.SOSResponse;
import com.sosesib.backend.models.responses.TimeSeriesResponseWithAggregations;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    public List<File> generateCSVFiles(TimeSeriesRequestWithAggregations request) throws IOException {
        List<TimeSeries> original_series = timeSeriesService.getTimeSeries(request);
        Map<String,List<Aggregation<Double>>> aggregations = timeSeriesService.getAggregations(request);

        for(TimeSeries ts : original_series){
            System.out.println(ts);
        }

        File original = new File("original.csv");
        ICsvBeanWriter csvWriter  = new CsvBeanWriter(new FileWriter(original), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeaderOriginal = {"measurementId","sensorId","measurementDate","measurementValue"};
        String[] csvHeaderAggregation = {"lowDate","highDate","aggregationValue"};
        List<File> files = new ArrayList<>();
        csvWriter.writeHeader(csvHeaderOriginal);
        for(TimeSeries ts : original_series){
            csvWriter.write(ts,csvHeaderOriginal);
        }
        csvWriter.close();
        files.add(original);
        for(String x : aggregations.keySet()){
            File file = new File(x+".csv");
            ICsvBeanWriter tmpWriter  = new CsvBeanWriter(new FileWriter(file), CsvPreference.STANDARD_PREFERENCE);
            tmpWriter.writeHeader(csvHeaderAggregation);
            for(Aggregation<Double> agg : aggregations.get(x)){
                tmpWriter.write(agg,csvHeaderAggregation);
            }
            tmpWriter.close();
            files.add(file);
        }
        return files;
    }

    @PostMapping("/downloadZip")
    public void downloadFile(HttpServletResponse response , @RequestBody TimeSeriesRequestWithAggregations request) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=download.zip");
        response.setStatus(HttpServletResponse.SC_OK);

        List<File> files = generateCSVFiles(request);

        try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
            for (File file : files) {
                FileSystemResource resource = new FileSystemResource(file);

                ZipEntry e = new ZipEntry(resource.getFilename());
                // Configure the zip entry, the properties of the file
                e.setSize(resource.contentLength());
                e.setTime(System.currentTimeMillis());
                // etc.
                zippedOut.putNextEntry(e);
                // And the content of the resource:
                StreamUtils.copy(resource.getInputStream(), zippedOut);
                zippedOut.closeEntry();
            }
            zippedOut.finish();
        } catch (Exception e) {
            // Exception handling goes here
        }
    }

}
