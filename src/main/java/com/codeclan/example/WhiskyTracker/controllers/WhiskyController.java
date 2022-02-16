package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(){
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/whiskies/{year}")
    public ResponseEntity getWhiskyByYear(@PathVariable int year){
        return new ResponseEntity(whiskyRepository.findByYear(year), HttpStatus.OK);
    }

    @GetMapping(value="/whiskies/{distilleryId}/{age}")
    public ResponseEntity getWhiskyByYear(@PathVariable Long distilleryId, @PathVariable int age){
        Distillery distillery = distilleryRepository.getById(distilleryId);
        return new ResponseEntity(whiskyRepository.findByDistilleryAndAge(distillery, age), HttpStatus.OK);
    }

}
