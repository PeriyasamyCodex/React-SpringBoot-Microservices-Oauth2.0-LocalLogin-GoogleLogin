package com.jtp.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtp.data.model.Attractions;
import com.jtp.data.model.Places;
import com.jtp.data.repository.AttractionsRepository;
import com.jtp.data.repository.PlacesRepository;

@Service
public class AttractionsService {

    @Autowired
    private PlacesRepository placesRepository;

    @Autowired
    private AttractionsRepository attractionsRepository;

    public List<Attractions> getAttractionsForPlace(String placeId) {
        Optional<Places> places = placesRepository.findById(placeId);
        if (!places.isPresent()) {
            return null;
        }
        List<String> attractionsIds = places.get().getAttractions_id();
        if (attractionsIds.isEmpty()) {
            return null;
        }
        return attractionsRepository.findBy_idIn(attractionsIds);

    }
}
