package com.sosesib.backend.services;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.entities.TimeSeries;

import java.util.List;

public interface TimeSeriesService {
    public List<TimeSeries> getBySensorId(Integer SensorId);
    public List<Aggregation<Double>> aggregationSum(int aggregationSize, int sensorId);
    public List<Aggregation<Double>> aggregationProd(int aggregationSize, int sensorId);
}
