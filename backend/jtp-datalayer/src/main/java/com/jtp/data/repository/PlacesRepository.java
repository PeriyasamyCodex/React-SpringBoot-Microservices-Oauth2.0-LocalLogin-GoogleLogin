package com.jtp.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jtp.data.model.Places;

@Repository
public interface PlacesRepository extends MongoRepository<Places, String>{

    
    
}
