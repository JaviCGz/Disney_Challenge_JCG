package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.BasicGenreDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    @Autowired
    MovieMapper movieMapper;

/*------------------------------- Entity-DTO Conversions -------------------------------*/

    public Genre convertToEntity (GenreDTO dto) {
        Genre entity = new Genre();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());

        return entity;
    }

    public GenreDTO convertToDTO (Genre entity, boolean loadMovies) {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());

        if (loadMovies) {
            List<MovieDTO> movieDTOS = movieMapper.convertToDTOList(entity.getMovies(),
                    false, false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }

/*------------------------------- Entity-DTO List Conversions -------------------------------*/

    public List<Genre> convertToEntityList (List<GenreDTO> dtoList) {
        List<Genre> entityList = new ArrayList<>();

        for (GenreDTO dto : dtoList) {
            entityList.add(convertToEntity(dto));
        }
        return entityList;
    }

    public List<GenreDTO> convertToDTOList (List<Genre> entityList, boolean loadMovies) {
        List<GenreDTO> dtoList = new ArrayList<>();

        for (Genre entity : entityList) {
            dtoList.add(convertToDTO(entity, loadMovies));
        }
        return dtoList;
    }

    public List<BasicGenreDTO> convertToBasicDTOList (List<Genre> entityList) {
        List<BasicGenreDTO> basicDTOList = new ArrayList<>();

        for (Genre entity : entityList) {
            basicDTOList.add(convertToBasicDTO(entity));
        }
        return basicDTOList;
    }
    
    /*------------------------------- Additional methods -------------------------------*/
    
    public void refreshValues (Genre entity, GenreDTO dto) {
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
    }
    
    /*------------------------------- Internal Methods -------------------------------*/

    private BasicGenreDTO convertToBasicDTO (Genre entity) {
        BasicGenreDTO basicDTO = new BasicGenreDTO();
        basicDTO.setName(entity.getName());
        basicDTO.setImage(entity.getImage());
        return basicDTO;
    }

//TODO: genreMapper -> lookForOrCreate()
}
