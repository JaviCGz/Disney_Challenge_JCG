package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);
    List<BasicCharacterDTO> getAll();
    void delete(Long id);
    CharacterDTO update(Long id, CharacterDTO dto);
    
}
