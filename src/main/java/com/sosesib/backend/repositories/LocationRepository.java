package com.sosesib.backend.repositories;

import com.sosesib.backend.models.entities.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends CrudRepository<Location, UUID> {
    List<Location> findAll();
}
