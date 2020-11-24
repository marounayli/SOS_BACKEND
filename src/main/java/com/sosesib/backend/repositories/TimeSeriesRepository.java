package com.sosesib.backend.repositories;

import com.sosesib.backend.models.entities.TimeSeries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TimeSeriesRepository extends CrudRepository<TimeSeries, UUID> {

     List<TimeSeries> findBySensorId(Integer sensorID);

     @Query(value = "select * from timeseries where sensor_id = :sensorId and measurement_date BETWEEN :startDateTime AND :endDateTime ", nativeQuery = true)
     List<TimeSeries> getTimeSeries(@Param("sensorId")Integer sensorId,
                                     @Param("startDateTime") LocalDateTime startDateTime,
                                     @Param("endDateTime") LocalDateTime endDateTime);

     @Query(value = "SELECT * FROM timeseries WHERE sensor_id = :sensorId ORDER BY measurement_date DESC LIMIT 1",nativeQuery = true)
     TimeSeries getLatestMeasurement(@Param("sensorId")Integer sensorId);
}
