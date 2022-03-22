package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.BasicMovieDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    
    MovieDTO save(MovieDTO dto);
    List<BasicMovieDTO> getAll();
    void delete(Long id);
    
}
