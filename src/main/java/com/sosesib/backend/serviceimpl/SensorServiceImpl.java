package com.sosesib.backend.serviceimpl;

import com.sosesib.backend.models.entities.Sensor;
import com.sosesib.backend.repositories.SensorRepository;
import com.sosesib.backend.services.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor getSensorById(Integer sensorId) {
        return sensorRepository.findBySensorId(sensorId);
    }

    @Override
    public List<Sensor> findSensorByRegion(String regionName) {
        return sensorRepository.findSensorByRegion(regionName);
    }

    @Override
    public List<Sensor> findSensorByCity(String city) {
        return sensorRepository.findSensorByCity(city);
    }
}
