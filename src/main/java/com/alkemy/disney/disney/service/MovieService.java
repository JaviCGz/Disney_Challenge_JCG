package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.BasicMovieDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;

import java.util.List;

public interface MovieService {
    
    MovieDTO save(MovieDTO dto);
    List<BasicMovieDTO> getAll();
    List<MovieDTO> getByFilters(String title, List<Long> genresId, String order);
    MovieDTO getDetails(Long id);
    void delete(Long id);
    MovieDTO update(Long id, MovieDTO dto);
    List<MovieDTO> convertToDTOList(List<Movie> entityList, boolean loadCharacters, boolean loadGenres);
}
