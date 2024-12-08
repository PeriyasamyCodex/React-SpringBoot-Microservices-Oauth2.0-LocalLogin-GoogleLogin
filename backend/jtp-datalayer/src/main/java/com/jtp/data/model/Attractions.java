package com.jtp.data.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document("attractions")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Attractions {
    
    private String _id;
    private String title;
    private String description;
    private String distanceFromCenter;
    private String location;
    private ArrayList<String> images;
    private String openTime;
    private String closeTime;
    private String spendDuration;
    private String RecommendedPersonType;
    private String PhysicalFitnessLevel;
    private String WheelchairAccessability;
    private String TerrainType;
    private String name;
}
