package com.sosesib.backend.services;

import com.sosesib.backend.models.entities.Sensor;

import java.util.List;
public interface SensorService {
    List<Sensor> getAllSensors();
}
