package com.jtp.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jtp.data.model.Attractions;
import java.util.List;



public interface AttractionsRepository extends MongoRepository<Attractions,String>{
    
    List<Attractions> findBy_idIn(List<String> ids);
    
}
