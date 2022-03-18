package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GenreDTO {

    private Long id;
    private String name;
    private String image;
    private List<Movie> movies = new ArrayList<>();
}
