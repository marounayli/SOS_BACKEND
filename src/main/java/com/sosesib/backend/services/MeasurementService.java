package com.sosesib.backend.services;

import com.sosesib.backend.models.entities.Measurement;

import java.util.List;
public interface MeasurementService {
    List<Measurement> getAllMeasurements();
}
