package com.sosesib.backend.repositories;

import com.sosesib.backend.models.entities.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SpecificationRepository extends CrudRepository<Specification, UUID> {
    List<Specification> findAll();
}
