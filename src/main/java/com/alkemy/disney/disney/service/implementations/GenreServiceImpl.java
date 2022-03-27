package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicGenreDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.NotFoundException;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreMapper genreMapper, GenreRepository genreRepository) {
        this.genreMapper = genreMapper;
        this.genreRepository = genreRepository;
    }

    public GenreDTO save (GenreDTO dto) {
        
        validateReceivedDTO(dto);
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
    
    public GenreDTO update (Long id, GenreDTO dto) {
        
        validateReceivedDTO(dto);
        Optional<Genre> entity = genreRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Genre ID. not found in database");
        }
        genreMapper.refreshValues(entity.get(), dto);
        Genre savedGenre = genreRepository.save(entity.get());
    
        return genreMapper.convertToDTO(savedGenre, false);
    }
    
    public List<GenreDTO> convertToDTOList (List<Genre> entityList, boolean loadMovies) {
        
        return genreMapper.convertToDTOList(entityList, loadMovies);
    }
    
    public List<Genre> lookForOrCreate (List<GenreDTO> dtoList) {
        List<Genre> entityList = new ArrayList<>();
        for (GenreDTO dto : dtoList) {
            if (dto.getId() != null) {
                Optional<Genre> result = genreRepository.findById(dto.getId());
                if (result.isPresent()) {
                    entityList.add(result.get());
                }  else {
                    throw new NotFoundException("A non-existent ID. was received." +
                            "New genres must not have an user-assign ID.");
                }
            } else {
                validateReceivedDTO(dto);
                entityList.add(genreMapper.convertToEntity(dto));
            }
        }
        
        return entityList;
    }
    
    private void validateReceivedDTO (GenreDTO dto) {
        if (dto == null) {
            throw new InvalidDTOException("No genre was received");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidDTOException("Genre must have a name");
        }
        if (dto.getImage() == null || dto.getImage().isBlank()) {
            throw new InvalidDTOException("Genre must have an image");
        }
    }
    
}
