package com.sosesib.backend.serviceimpl;


import com.sosesib.backend.functional.Aggregator;
import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.entities.TimeSeries;
import com.sosesib.backend.models.requests.TimeSeriesRequestWithAggregations;
import com.sosesib.backend.repositories.TimeSeriesRepository;
import com.sosesib.backend.services.TimeSeriesService;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class TimeSeriesServiceImpl implements TimeSeriesService {

    private final TimeSeriesRepository timeSeriesRepository;
    private final Map<String, Aggregator<Double,Integer>> aggregatorMap;

    public TimeSeriesServiceImpl(TimeSeriesRepository timeSeriesRepository) {
        this.timeSeriesRepository = timeSeriesRepository;
        aggregatorMap = new HashMap<>();
        aggregatorMap.put("sum", this::aggregationSum);
        aggregatorMap.put("prod", this::aggregationProd);
        aggregatorMap.put("avg", this::aggregationAvg);
        aggregatorMap.put("range", this::aggregationRange);
        aggregatorMap.put("min", this::aggregationMin);
        aggregatorMap.put("max", this::aggregationMax);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<TimeSeries> getAllSeriesBySensorId(Integer sensorId) {
        return timeSeriesRepository.findBySensorId(sensorId);
    }

    @Override
    public List<TimeSeries> getTimeSeries(TimeSeriesRequestWithAggregations request) {
        for(int i=0;i<10;++i){
            System.out.println(request.getStartDateTime().format(formatter));
        }
        return timeSeriesRepository.getTimeSeries(request.getSensorId(),request.getStartDateTime(),request.getEndDateTime());
    }

    @Override
    public List<Aggregation<Double>> aggregationSum(TimeSeriesRequestWithAggregations request) {
        return aggregationGenerator(request,(x)->x.stream().reduce(0.0,Double::sum));
    }

    @Override
    public List<Aggregation<Double>> aggregationProd(TimeSeriesRequestWithAggregations request) {
        return aggregationGenerator(request,(x)->x.stream().reduce(1.0,(y,z)->y*z));
    }

    @Override
    public List<Aggregation<Double>> aggregationMax(TimeSeriesRequestWithAggregations request) {
        return aggregationGenerator(request , (x)->x.stream().reduce(0.0,(y,z)->y>z?y:z));
    }


    @Override
    public List<Aggregation<Double>> aggregationMin(TimeSeriesRequestWithAggregations request) {
        return aggregationGenerator(request, (x)->x.stream().reduce(0.0,(y,z)->y<z?y:z));
    }

    @Override
    public List<Aggregation<Double>> aggregationAvg(TimeSeriesRequestWithAggregations request) {
        return aggregationGenerator(request, (x)->x.stream().mapToDouble(y->y).average().orElse(0.0));
    }

    @Override
    public List<Aggregation<Double>> aggregationRange(TimeSeriesRequestWithAggregations request) {
        return aggregationGenerator(request, this::ListRange);
    }


    private  List<Aggregation<Double>> aggregationGenerator(TimeSeriesRequestWithAggregations request , Function<List<Double>,Double> aggregator) {
        List<TimeSeries> timeSeries = this.getTimeSeries(request);
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
            for(loopCounter=0; loopCounter<request.getAggregationSize();++loopCounter){
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

    public Map<String,List<Aggregation<Double>>> getAggregations(TimeSeriesRequestWithAggregations request){
        Map<String,List<Aggregation<Double>>> map = new HashMap<>();
        for(String agg : request.getAggregations()){
            map.put(agg,aggregatorMap.get(agg).process(request));
        }
        return map;
    }
}
