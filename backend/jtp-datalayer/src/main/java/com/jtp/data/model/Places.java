package com.jtp.data.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document("places")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Places {
    
    @Id
    private String _id;
    private String title;
    private String description;
    private ArrayList<String> attractions_id;
    private String name;
    private String ReachBy;
    private String image;
    private String DaysRecommended;
    private String loacation;

}
