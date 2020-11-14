package com.sosesib.backend.serviceimpl;


import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.TimeSeries;
import com.sosesib.backend.repositories.TimeSeriesRepository;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSeriesServiceImpl implements TimeSeriesService {

    private final TimeSeriesRepository timeSeriesRepository;

    public TimeSeriesServiceImpl(TimeSeriesRepository timeSeriesRepository) {
        this.timeSeriesRepository = timeSeriesRepository;
    }

    @Override
    public List<TimeSeries> getBySensorId(Integer sensorId) {
        return timeSeriesRepository.findBySensorId(sensorId);
    }

    @Override
    public List<Aggregation> aggregationSum(int aggregationSize,int sensorId) {
        List<TimeSeries> timeSeries = timeSeriesRepository.findBySensorId(sensorId);
        List<Aggregation> aggregations = new ArrayList<>();
        int seriesSize=timeSeries.size();
        if(seriesSize==0)
            return aggregations;
        int refCounter =0;
        while(refCounter<seriesSize){
            int loopCounter=0;
            Double aggValue=0.0;
            Aggregation aggregation = new Aggregation();
            aggregation.setLowDate(timeSeries.get(refCounter).getMeasurementDate());
            for(loopCounter=0; loopCounter<aggregationSize;++loopCounter){
                aggValue+=timeSeries.get(refCounter).getMeasurementValue();
                refCounter++;
                if(refCounter==timeSeries.size())
                    break;
            }
            aggregation.setAggregationValue(aggValue);
            if(refCounter==seriesSize) {
                aggregation.setHighDate(timeSeries.get(refCounter - 1).getMeasurementDate());
            }
            else{
                aggregation.setHighDate(timeSeries.get(refCounter).getMeasurementDate());
            }
            aggregations.add(aggregation);
        }
        return aggregations;
    }
}
