package com.sosesib.backend;

import com.sosesib.backend.models.Identification;
import com.sosesib.backend.models.RandomRange;
import com.sosesib.backend.models.SOSResponse;
import com.sosesib.backend.services.RandomNumbers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    private final RandomNumbers randomNumbersService;

    public DummyController(RandomNumbers randomNumbers) {
        this.randomNumbersService = randomNumbers;
    }

    @GetMapping("/hello")
    public String hello( @RequestParam(value = "name", defaultValue = "") String name){
        return "Hello"+name;
    }

    @PostMapping("list")
    public Map<LocalDate,Integer> id(@RequestBody RandomRange randomRange){
        return randomNumbersService.getRandomSequence(randomRange);
    }

    @PostMapping("/person")
    public String identify(@RequestBody Identification identification){
        return identification.toString();
    }

    @GetMapping("/create")
    public ResponseEntity<SOSResponse<Identification>> identify(@RequestParam String userId){
        try {
            if (userId.equals("jean")) {
                throw new IllegalAccessException("ntek l wade3");
            }
            Identification id = new Identification("jean", 22, "esib");
            SOSResponse<Identification> response = new SOSResponse<>("SUCCESS" , "CREATED" , id);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (IllegalAccessException e){
            SOSResponse<Identification> response = new SOSResponse<>("Failure" , "Not allowed" , null);
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

    }
}
