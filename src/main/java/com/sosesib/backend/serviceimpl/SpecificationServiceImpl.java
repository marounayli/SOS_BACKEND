package com.sosesib.backend.serviceimpl;

import com.sosesib.backend.models.entities.Specification;
import com.sosesib.backend.repositories.SpecificationRepository;
import com.sosesib.backend.services.SpecificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    private final SpecificationRepository specificationRepository;

    public SpecificationServiceImpl(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @Override
    public List<Specification> getAllSpecifications(){
        return specificationRepository.findAll();
    }
}
