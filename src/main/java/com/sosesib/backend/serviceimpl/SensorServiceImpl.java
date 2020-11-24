package com.sosesib.backend.serviceimpl;

import com.sosesib.backend.models.entities.Sensor;
import com.sosesib.backend.models.entities.TimeSeries;
import com.sosesib.backend.repositories.SensorRepository;
import com.sosesib.backend.repositories.TimeSeriesRepository;
import com.sosesib.backend.services.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final TimeSeriesRepository timeSeriesRepository;

    public SensorServiceImpl(SensorRepository sensorRepository, TimeSeriesRepository timeSeriesRepository) {
        this.sensorRepository = sensorRepository;
        this.timeSeriesRepository = timeSeriesRepository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return  addLatestMeasurements(sensors);
    }

    @Override
    public Sensor getSensorById(Integer sensorId) {
        Sensor sensor = sensorRepository.findBySensorId(sensorId);
        sensor.setLastMeasurement(getLatestMeasurement(sensorId));
        return sensor;
    }

    @Override
    public List<Sensor> findSensorByRegion(String regionName) {
        List<Sensor> sensors =  sensorRepository.findSensorByRegion(regionName);
        return addLatestMeasurements(sensors);
    }

    @Override
    public List<Sensor> findSensorByCity(String city) {
        List<Sensor> sensors =  sensorRepository.findSensorByCity(city);
        return addLatestMeasurements(sensors);
    }

    @Override
    public TimeSeries getLatestMeasurement(Integer sensorId) {
        return timeSeriesRepository.getLatestMeasurement(sensorId);
    }

    private List<Sensor> addLatestMeasurements(List<Sensor> sensors){
        for(Sensor sens : sensors){
            sens.setLastMeasurement(getLatestMeasurement(sens.getSensorId()));
        }
        return sensors;
    }
}
