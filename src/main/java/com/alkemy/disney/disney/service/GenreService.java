package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.BasicGenreDTO;
import com.alkemy.disney.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    
    List<BasicGenreDTO> getAll();
    GenreDTO save(GenreDTO dto);
    void delete(Long id);
    
}
