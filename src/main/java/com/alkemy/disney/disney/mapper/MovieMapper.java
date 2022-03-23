package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.BasicMovieDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {
    
    @Autowired
    GenreMapper genreMapper;
    @Autowired
    CharacterMapper characterMapper;
    
    /*------------------------------- Entity-DTO Conversions -------------------------------*/
    
    public Movie convertToEntity(MovieDTO dto) {
        Movie entity = new Movie();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(convertStringToLocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
//consider adding movies and genres list in saving process
        return entity;
    }
    
    public MovieDTO convertToDTO(Movie entity, boolean loadGenres, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setRating(entity.getRating());
    
        if (loadGenres) {
            List<GenreDTO> genreDTOS = genreMapper.convertToDTOList(entity.getGenres(),
                    false);
            dto.setGenres(genreDTOS);
        }
    
        if (loadCharacters) {
            List<CharacterDTO> characterDTOS = characterMapper.convertToDTOList(entity.getCharacters(),
                    false);
            dto.setCharacters(characterDTOS);
        }
        return dto;
    }
    
    /*------------------------------- Entity-DTO List Conversions -------------------------------*/
    
    public List<Movie> convertToEntityList (List<MovieDTO> dtoList) {
        List<Movie> entityList = new ArrayList<>();
    
        for (MovieDTO dto : dtoList) {
            entityList.add(convertToEntity(dto));
        }
        return entityList;
    }
    
    public List<MovieDTO> convertToDTOList (List<Movie> entityList, boolean loadCharacters, boolean loadGenres) {
        List<MovieDTO> dtoList = new ArrayList<>();
    
        for (Movie entity : entityList) {
            dtoList.add(convertToDTO(entity, false, false));
        }
        return dtoList;
    }
    
    public List<BasicMovieDTO> convertToBasicDTOList(List<Movie> entityList) {
        List<BasicMovieDTO> basicDTOList = new ArrayList<>();
    
        for (Movie entity : entityList) {
            basicDTOList.add(convertToBasicDTO(entity));
        }
        return basicDTOList;
    }
    
    /*------------------------------- Additional methods -------------------------------*/
    
    public void refreshValues(Movie entity, MovieDTO dto) {
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(convertStringToLocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
    }
    
    
    
    /*------------------------------- Internal Methods -------------------------------*/
    
    private LocalDate convertStringToLocalDate (String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(stringDate, formatter);
    }
    
    private BasicMovieDTO convertToBasicDTO (Movie entity) {
        BasicMovieDTO basicDTO = new BasicMovieDTO();
        basicDTO.setImage(entity.getImage());
        basicDTO.setTitle(entity.getTitle());
        basicDTO.setCreationDate(entity.getCreationDate().toString());
        return basicDTO;
    }
//TODO: movieMapper -> lookForOrCreate()
}
