package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public Character convertToEntity(CharacterDTO dto) {
        Character entity = new Character();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        entity.setMovies(dto.getMovies());
        return entity;
    }

    public  CharacterDTO convertToDto(Character entity) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        dto.setMovies(entity.getMovies());
        return dto;
    }
}
