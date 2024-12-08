package com.jtp.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.jtp.data.model.Places;
import com.jtp.data.repository.PlacesRepository;


@SpringBootApplication
@EnableMongoRepositories
@EnableDiscoveryClient
public class JTPDataLayerApplication implements CommandLineRunner{

	@Autowired
	PlacesRepository placesRepository;

	public static void main(String[] args) {
		SpringApplication.run(JTPDataLayerApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		List<Places> places = placesRepository.findAll();
		places.forEach(place -> System.out.println(place.toString()));
	}

}
