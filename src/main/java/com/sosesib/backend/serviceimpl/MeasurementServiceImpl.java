package com.sosesib.backend.serviceimpl;

import com.sosesib.backend.models.entities.Measurement;
import com.sosesib.backend.repositories.MeasurementRepository;
import com.sosesib.backend.services.MeasurementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }
}
