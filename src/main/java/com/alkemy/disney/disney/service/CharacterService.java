package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Character;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);
    List<BasicCharacterDTO> getAll();
    CharacterDTO getDetails(Long id);
    void delete(Long id);
    CharacterDTO update(Long id, CharacterDTO dto);
    List<CharacterDTO> convertToDTOList(List<Character> entityList, boolean loadMovies);
    List<Character> lookForOrCreate(List<CharacterDTO> dtoList);
    List<CharacterDTO> getByFilters(String name, Integer age, List<Long> movies);
}
