package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicMovieDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    
    public MovieServiceImpl(MovieMapper movieMapper, MovieRepository movieRepository) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
    }
    
//TODO: Add boolean parameter in movieMapper->convertToEntity for save genre if it boolean is true
    public MovieDTO save(MovieDTO dto) {
        
        validateReceivedDTO(dto);
        Movie entity = movieMapper.convertToEntity(dto);
        Movie savedEntity = movieRepository.save(entity);
        
        return movieMapper.convertToDTO(savedEntity, false, false);
    }
    
    public List<BasicMovieDTO> getAll() {
        List<Movie> entities = movieRepository.findAll();
        
        return movieMapper.convertToBasicDTOList(entities);
    }
    
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
    
    public MovieDTO update(Long id, MovieDTO dto) {
        
        validateReceivedDTO(dto);
        Optional<Movie> entity = movieRepository.findById(id);
        if (entity.isEmpty()) {
        throw new ParamNotFound("Movie ID. not found in database");
        }
        movieMapper.refreshValues(entity.get(), dto);
        Movie savedEntity = movieRepository.save(entity.get());
    
        return movieMapper.convertToDTO(savedEntity, false, false);
    }
    
    private void validateReceivedDTO(MovieDTO dto) {
        if (dto == null) {
            throw new InvalidDTOException("No movie was received");
        }
        if (dto.getImage() == null || dto.getImage().isBlank()) {
            throw new InvalidDTOException("Movie must have an image");
        }
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new InvalidDTOException("Movie must have a title");
        }
        if (dto.getCreationDate() == null || dto.getCreationDate().isBlank()) {
            throw new InvalidDTOException("Movie must have a creation date");
        }
        if (dto.getRating() == null) {
            throw new InvalidDTOException("Movie must have a rating");
        }
    }
}
