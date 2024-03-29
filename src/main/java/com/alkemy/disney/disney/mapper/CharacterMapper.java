package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {
    
    private final MovieService movieService;
    
    //@Lazy annotation used to avoid circular references
    @Autowired
    public CharacterMapper(@Lazy MovieService movieService) {
        this.movieService = movieService;
    }
    
    /*------------------------------- Entity-DTO Conversions -------------------------------*/
    
    public Character convertToEntity(CharacterDTO dto) {
        Character entity = new Character();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        return entity;
    }
    
    public CharacterDTO convertToDTO(Character entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        
        if (loadMovies) {
            List<MovieDTO> movieDTOS = movieService.convertToDTOList(entity.getMovies(),
                    false, false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }
    
    /*------------------------------- Entity-DTO List Conversions -------------------------------*/
    
    public List<Character> convertToEntityList(List<CharacterDTO> dtoList) {
        List<Character> entityList = new ArrayList<>();
        
        for (CharacterDTO dto : dtoList) {
            entityList.add(convertToEntity(dto));
        }
        return entityList;
    }
    
    public List<CharacterDTO> convertToDTOList(List<Character> entityList, boolean loadMovies) {
        List<CharacterDTO> dtoList = new ArrayList<>();
        
        for (Character entity : entityList) {
            dtoList.add(convertToDTO(entity, loadMovies));
        }
        return dtoList;
    }
    
    public List<BasicCharacterDTO> convertToBasicDTOList(List<Character> entityList) {
        List<BasicCharacterDTO> basicDTOList = new ArrayList<>();
        
        for (Character entity : entityList) {
            basicDTOList.add(convertToBasicDTO(entity));
        }
        return basicDTOList;
    }
    
    /*------------------------------- Additional methods -------------------------------*/
    
    public void refreshValues(Character entity, CharacterDTO dto) {
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
    }
    
    /*------------------------------- Internal Methods -------------------------------*/
    
    private BasicCharacterDTO convertToBasicDTO(Character entity) {
        BasicCharacterDTO basicDTO = new BasicCharacterDTO();
        basicDTO.setImage(entity.getImage());
        basicDTO.setName(entity.getName());
        return basicDTO;
    }
    
}