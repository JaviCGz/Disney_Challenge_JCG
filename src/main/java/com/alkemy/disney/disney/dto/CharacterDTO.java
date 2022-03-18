package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharacterDTO {
    private long id;
    private String image;
    private String name;
    private int age;
    private double weight;
    private String story;
    private List<Movie> movies = new ArrayList<>();
}
