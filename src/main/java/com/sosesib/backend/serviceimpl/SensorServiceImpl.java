package com.sosesib.backend.serviceimpl;

import com.sosesib.backend.models.Sensor;
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
}
