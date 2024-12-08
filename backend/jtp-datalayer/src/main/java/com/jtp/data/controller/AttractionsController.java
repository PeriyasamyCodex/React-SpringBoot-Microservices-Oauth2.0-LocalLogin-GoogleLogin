package com.jtp.data.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtp.data.model.Attractions;
import com.jtp.data.service.AttractionsService;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/backendApi/attractions")
public class AttractionsController {

    @Autowired
    private AttractionsService attractionsService;


    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public List<Attractions> getAttractions(@PathVariable(name = "id") String id) {
        
        return attractionsService.getAttractionsForPlace(id);
    }


}
