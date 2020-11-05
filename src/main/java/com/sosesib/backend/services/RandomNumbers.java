package com.sosesib.backend.services;

import com.sosesib.backend.models.RandomRange;

import java.time.LocalDate;
import java.util.Map;

public interface RandomNumbers {
    public Map<LocalDate,Integer> getRandomSequence(RandomRange randomRange);
}
