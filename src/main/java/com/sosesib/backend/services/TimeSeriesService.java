package com.sosesib.backend.services;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.entities.TimeSeries;

import java.util.List;

public interface TimeSeriesService {
    List<TimeSeries> getBySensorId(Integer SensorId);
    List<Aggregation<Double>> aggregationSum(int aggregationSize, int sensorId);
    List<Aggregation<Double>> aggregationProd(int aggregationSize, int sensorId);
    List<Aggregation<Double>> aggregationMax(int aggregationSize, int sensorId);
    List<Aggregation<Double>> aggregationMin(int aggregationSize, int sensorId);
    List<Aggregation<Double>> aggregationAvg(int aggregationSize, int sensorId);
    List<Aggregation<Double>> aggregationRange(int aggregationSize, int sensorId);

}
