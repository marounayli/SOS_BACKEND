package com.sosesib.backend.serviceimpl;

import com.sosesib.backend.models.RandomRange;
import com.sosesib.backend.services.RandomNumbers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RandomNumbersImpl implements RandomNumbers {
    @Override
    public Map<LocalDate, Integer> getRandomSequence(RandomRange randomRange) {
        Random rand =  new Random();
        Map<LocalDate,Integer> map = new HashMap<>();
        for (int i=0; i<randomRange.getHigh(); ++i)
        {
            LocalDate date = LocalDate.now().plusDays(i);
            map.put(date,rand.nextInt(100));
        }
        return map;
    }
}
