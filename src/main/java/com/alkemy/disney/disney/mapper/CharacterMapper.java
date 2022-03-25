package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CharacterMapper {

    @Autowired
    MovieMapper movieMapper;
    
    @Autowired
    CharacterRepository characterRepository;

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

    public  CharacterDTO convertToDTO(Character entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());

        if (loadMovies) {
            List<MovieDTO> movieDTOS = movieMapper.convertToDTOList(entity.getMovies(),
                    false, false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }

/*------------------------------- Entity-DTO List Conversions -------------------------------*/

    public List<Character> convertToEntityList (List<CharacterDTO> dtoList) {
        List<Character> entityList = new ArrayList<>();

        for (CharacterDTO dto : dtoList) {
            entityList.add(convertToEntity(dto));
        }
        return entityList;
    }

    public List<CharacterDTO> convertToDTOList (List<Character> entityList, boolean loadMovies) {
        List<CharacterDTO> dtoList = new ArrayList<>();

        for (Character entity : entityList) {
            dtoList.add(convertToDTO(entity, loadMovies));
        }
        return dtoList;
    }
    
    public List<BasicCharacterDTO> convertToBasicDTOList (List<Character> entityList) {
        List<BasicCharacterDTO> basicDTOList = new ArrayList<>();
        
        for (Character entity : entityList) {
            basicDTOList.add(convertToBasicDTO(entity));
        }
        return basicDTOList;
    }
    
    /*------------------------------- Additional methods -------------------------------*/
    
    public void refreshValues (Character entity, CharacterDTO dto) {
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
    }
    
    public List<Character> lookForOrCreateCharacter(List<CharacterDTO> dtoList) {
        List<Character> entityList = new ArrayList<>();
        for(CharacterDTO dto : dtoList) {
            if (dto.getId() != null) {
                Optional<Character> result = characterRepository.findById(dto.getId());
                if (result.isPresent()) {
                    entityList.add(result.get());
                } else {
                    throw new ParamNotFound("A non-existent ID. was received." +
                            "New characters must not have an user-assign ID.");
                }
            } else {
                entityList.add(convertToEntity(dto));
            }
        }
        return entityList;
    }
    
    /*------------------------------- Internal Methods -------------------------------*/
    
    private BasicCharacterDTO convertToBasicDTO (Character entity) {
        BasicCharacterDTO basicDTO = new BasicCharacterDTO();
        basicDTO.setImage(entity.getImage());
        basicDTO.setName(entity.getName());
        return basicDTO;
    }
    
}