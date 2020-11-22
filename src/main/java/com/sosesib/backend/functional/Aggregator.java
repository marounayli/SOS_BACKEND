package com.sosesib.backend.functional;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.requests.TimeSeriesRequestWithAggregations;

import java.time.LocalDateTime;
import java.util.List;

@FunctionalInterface
public interface Aggregator<X extends Number , Y extends Number> {
    List<Aggregation<X>> process(TimeSeriesRequestWithAggregations request);
}
