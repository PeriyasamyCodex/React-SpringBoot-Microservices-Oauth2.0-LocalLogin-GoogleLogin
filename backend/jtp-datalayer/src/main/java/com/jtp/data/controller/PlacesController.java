package com.jtp.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jtp.data.model.Places;
import com.jtp.data.repository.PlacesRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/backendApi/places")
public class PlacesController {

    @Autowired
    private PlacesRepository placesRepository;    

    @GetMapping("/allPlaces")
    public List<Places> getPlaces() {
        
        return placesRepository.findAll();
    }


}
