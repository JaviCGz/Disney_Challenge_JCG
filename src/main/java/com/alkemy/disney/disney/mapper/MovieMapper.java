package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    public List<MovieDTO> convertToDTOList (List<Movie> entityList, boolean loadCharacters, boolean loadGenres) {
        List<MovieDTO> dtoList = new ArrayList<>();

        return dtoList;
    }
}
