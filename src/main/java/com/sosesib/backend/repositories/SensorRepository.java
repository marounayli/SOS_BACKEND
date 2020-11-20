package com.sosesib.backend.repositories;

import com.sosesib.backend.models.entities.Sensor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor,Integer> {

    @Override
    @NonNull
    List<Sensor> findAll();

    Sensor findBySensorId(Integer sensorId);


    @Query(value = "select s.* from sensors s left outer join location l on s.location_id = l.location_id where l.region = :regionName",
            nativeQuery = true)
    List<Sensor> findSensorByRegion(@Param("regionName")String regionName);

    @Query(value = "select s.* from sensors s left outer join location l on s.location_id = l.location_id where l.city = :cityName",
            nativeQuery = true)
    List<Sensor> findSensorByCity(@Param("cityName")String regionName);

}
