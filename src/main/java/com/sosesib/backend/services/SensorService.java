package com.sosesib.backend.services;

import com.sosesib.backend.models.entities.Sensor;
import com.sosesib.backend.models.entities.TimeSeries;

import java.util.List;
public interface SensorService {
    List<Sensor> getAllSensors();
    Sensor getSensorById(Integer sensorId);
    List<Sensor> findSensorByRegion(String regionName);
    List<Sensor> findSensorByCity(String city);
    TimeSeries getLatestMeasurement(Integer sensorId);
}
