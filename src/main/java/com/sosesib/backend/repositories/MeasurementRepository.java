package com.sosesib.backend.repositories;

import com.sosesib.backend.models.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MeasurementRepository  extends CrudRepository<Measurement, UUID> {
    List<Measurement> findAll();
}
