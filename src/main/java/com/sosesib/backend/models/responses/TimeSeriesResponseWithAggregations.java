package com.sosesib.backend.models.responses;

import com.sosesib.backend.models.Aggregation;
import com.sosesib.backend.models.entities.TimeSeries;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class TimeSeriesResponseWithAggregations {
    List<TimeSeries> original;
    Map<String, List<Aggregation<Double>>> aggregationMap;
}
