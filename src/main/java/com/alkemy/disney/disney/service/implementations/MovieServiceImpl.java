package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicMovieDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    
    public MovieServiceImpl(MovieMapper movieMapper, MovieRepository movieRepository) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
    }
    
//TODO: Add boolean parameter in movieMapper->convertToEntity for save genre if it it boolean is true
    public MovieDTO save(MovieDTO dto) {
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
    
}
