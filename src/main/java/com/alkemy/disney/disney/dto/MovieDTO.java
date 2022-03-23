package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private int rating;
    private List<CharacterDTO> characters = new ArrayList<>();
    private List<GenreDTO> genres = new ArrayList<>();

}
