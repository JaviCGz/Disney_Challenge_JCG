package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private int rating;
    private List<Character> characters = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();

}
