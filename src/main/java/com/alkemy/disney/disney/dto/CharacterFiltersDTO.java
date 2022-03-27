package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private List<Long> moviesId;
    
    public CharacterFiltersDTO(String name, Integer age, List<Long> moviesId) {
        this.name = name;
        this.age = age;
        this.moviesId = moviesId;
    }
}
