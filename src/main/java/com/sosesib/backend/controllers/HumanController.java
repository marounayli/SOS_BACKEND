package com.sosesib.backend.controllers;

import com.sosesib.backend.models.Human;
import com.sosesib.backend.repositories.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/humans")
public class HumanController {
    @Autowired
    private final HumanRepository humanRepository;

    public HumanController(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @GetMapping("")
    public void getAllHumans(){
        humanRepository.save(new Human("xcz","pepehands","pepehands",10));
    }

    @GetMapping("/{id}")
    public Human getHumanById(@PathVariable String id){
        return humanRepository.findById(id).orElse(null);
    }
}
