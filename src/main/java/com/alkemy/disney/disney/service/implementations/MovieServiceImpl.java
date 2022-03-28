package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicMovieDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.NotFoundException;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final MovieSpecification movieSpecification;
    
    public MovieServiceImpl(MovieMapper movieMapper, MovieRepository movieRepository, MovieSpecification movieSpecification) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
        this.movieSpecification = movieSpecification;
    }
    
//TODO: Add boolean parameter in movieMapper->convertToEntity for save genre if it boolean is true
    public MovieDTO save(MovieDTO dto) {
        
        validateReceivedDTO(dto, true);
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
        
        validateReceivedDTO(dto, false);
        Optional<Movie> entity = movieRepository.findById(id);
        if (entity.isEmpty()) {
        throw new NotFoundException("Movie ID. not found in database");
        }
        movieMapper.refreshValues(entity.get(), dto);
        Movie savedEntity = movieRepository.save(entity.get());
    
        return movieMapper.convertToDTO(savedEntity, false, false);
    }
    
    public List<MovieDTO> getByFilters(String title, List<Long> genresId, String order) {
        
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, genresId, order);
        List<Movie> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        
        return this.movieMapper.convertToDTOList(entities, true, true);
    }
    
    public MovieDTO getDetails(Long id) {
        Optional<Movie> result = movieRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Invalid movie request. Id does not exist");
        }
        return movieMapper.convertToDTO(result.get(), true, true);
    }
    
    public List<MovieDTO> convertToDTOList (List<Movie> entityList, boolean loadCharacters, boolean loadGenres) {
    
        return movieMapper.convertToDTOList(entityList, loadCharacters, loadGenres);
    }
    
    private void validateReceivedDTO(MovieDTO dto, boolean extraValidation) {
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
        
        if (extraValidation) {
            if (dto.getCharacters() == null || dto.getCharacters().isEmpty()) {
                throw new InvalidDTOException("Movie must have at least one character");
            }
            if (dto.getGenres() == null || dto.getGenres().isEmpty()) {
                throw new InvalidDTOException("Movie must belong to some genre");
            }
        }
    }
    
}
