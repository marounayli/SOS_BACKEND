package com.sosesib.backend.serviceimpl;


import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.entities.TimeSeries;
import com.sosesib.backend.repositories.TimeSeriesRepository;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
    public List<Aggregation<Double>> aggregationSum(int aggregationSize, int sensorId) {
        return aggregationGenerator(aggregationSize,sensorId,(x)->x.stream().reduce(0.0,Double::sum));
    }

    @Override
    public List<Aggregation<Double>> aggregationProd(int aggregationSize, int sensorId) {
        return aggregationGenerator(aggregationSize,sensorId, (x)->x.stream().reduce(1.0,(y,z)->y*z));
    }

    @Override
    public List<Aggregation<Double>> aggregationMax(int aggregationSize, int sensorId) {
        return aggregationGenerator(aggregationSize,sensorId, (x)->x.stream().reduce(0.0,(y,z)->y>z?y:z));
    }


    @Override
    public List<Aggregation<Double>> aggregationMin(int aggregationSize, int sensorId) {
        return aggregationGenerator(aggregationSize,sensorId, (x)->x.stream().reduce(0.0,(y,z)->y<z?y:z));
    }

    @Override
    public List<Aggregation<Double>> aggregationAvg(int aggregationSize, int sensorId) {
        return aggregationGenerator(aggregationSize,sensorId, (x)->x.stream().mapToDouble(y->y).average().orElse(0.0));
    }

    @Override
    public List<Aggregation<Double>> aggregationRange(int aggregationSize, int sensorId) {
        return aggregationGenerator(aggregationSize,sensorId, this::ListRange);
    }


    private  List<Aggregation<Double>> aggregationGenerator(int aggregationSize, int sensorId, Function<List<Double>,Double> aggregator) {
        List<TimeSeries> timeSeries = timeSeriesRepository.findBySensorId(sensorId);
        List<Aggregation<Double>> aggregations = new ArrayList<>();
        int seriesSize=timeSeries.size();
        if(seriesSize==0)
            return aggregations;
        int refCounter =0;
        while(refCounter<seriesSize){
            int loopCounter;
            List<Double> aggList= new ArrayList<>();
            Aggregation<Double> aggregation = new Aggregation<>();
            aggregation.setLowDate(timeSeries.get(refCounter).getMeasurementDate());
            for(loopCounter=0; loopCounter<aggregationSize;++loopCounter){
                aggList.add(timeSeries.get(refCounter).getMeasurementValue());
                refCounter++;
                if(refCounter==timeSeries.size())
                    break;
            }
            aggregation.setAggregationValue(aggregator.apply(aggList));
            aggregation.setHighDate(timeSeries.get(refCounter - 1).getMeasurementDate());
            aggregations.add(aggregation);
        }
        return aggregations;
    }

    private Double ListRange(List<Double> data){

        if(data==null){
            return 0.0;
        }
        double min= Double.MAX_VALUE , max=Double.MIN_VALUE;
        for(Double x : data){
            if(x<min){
                min=x;
            }
            if(x>max){
                max=x;
            }
        }
        return max-min;
    }

}
