package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicGenreDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreMapper genreMapper, GenreRepository genreRepository) {
        this.genreMapper = genreMapper;
        this.genreRepository = genreRepository;
    }

    public GenreDTO save (GenreDTO dto) {
        Genre entity = genreMapper.convertToEntity(dto);
        Genre savedEntity = genreRepository.save(entity);
//Movies are not going to be created here
        return genreMapper.convertToDTO(savedEntity, false);
    }

    public List<BasicGenreDTO> getAll () {
        List<Genre> entities = genreRepository.findAll();
//In this method I used to call convertToBasicDTOList() method which does not need a boolean parameter
        return genreMapper.convertToBasicDTOList(entities);
    }
    
    public void delete (Long id) {
        genreRepository.deleteById(id);
    }

}
