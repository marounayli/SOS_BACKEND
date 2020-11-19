package com.sosesib.backend.repositories;

import com.sosesib.backend.models.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor,Integer> {
    List<Sensor> findAll();
}
