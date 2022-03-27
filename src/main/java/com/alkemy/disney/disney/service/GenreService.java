package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.BasicGenreDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;

import java.util.List;

public interface GenreService {
    
    List<BasicGenreDTO> getAll();
    GenreDTO save(GenreDTO dto);
    void delete(Long id);
    GenreDTO update(Long id, GenreDTO dto);
    List<GenreDTO> convertToDTOList(List<Genre> entityList, boolean loadMovies);
    List<Genre> lookForOrCreate(List<GenreDTO> dtoList);
}
