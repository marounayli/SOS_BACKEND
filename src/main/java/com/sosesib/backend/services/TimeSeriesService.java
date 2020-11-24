package com.sosesib.backend.services;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.entities.TimeSeries;
import com.sosesib.backend.models.requests.TimeSeriesRequestWithAggregations;

import java.util.List;
import java.util.Map;

public interface TimeSeriesService {
    List<TimeSeries> getAllSeriesBySensorId(Integer sensorId);
    List<TimeSeries> getTimeSeries(TimeSeriesRequestWithAggregations request);
    List<Aggregation<Double>> aggregationSum(TimeSeriesRequestWithAggregations request);
    List<Aggregation<Double>> aggregationProd(TimeSeriesRequestWithAggregations request);
    List<Aggregation<Double>> aggregationMax(TimeSeriesRequestWithAggregations request);
    List<Aggregation<Double>> aggregationMin(TimeSeriesRequestWithAggregations request);
    List<Aggregation<Double>> aggregationAvg(TimeSeriesRequestWithAggregations request);
    List<Aggregation<Double>> aggregationRange(TimeSeriesRequestWithAggregations request);
    Map<String,List<Aggregation<Double>>> getAggregations(TimeSeriesRequestWithAggregations request);
}
