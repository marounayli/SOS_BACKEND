package com.sosesib.backend.services;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.TimeSeries;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSeriesService {
    public List<TimeSeries> getBySensorId(Integer SensorId);
    public List<Aggregation> aggregationSum(int aggregationSize, int sensorId);
}
