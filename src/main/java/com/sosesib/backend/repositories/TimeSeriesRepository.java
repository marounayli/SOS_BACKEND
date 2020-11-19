package com.sosesib.backend.repositories;

import com.sosesib.backend.models.entities.TimeSeries;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TimeSeriesRepository extends CrudRepository<TimeSeries, UUID> {

    public List<TimeSeries> findBySensorId(Integer sensorId);
}
