package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;

    public CharacterDTO save(CharacterDTO dto) {
        Character entity = characterMapper.convertToEntity(dto);
        Character entitySaved = characterRepository.save(entity);
        CharacterDTO dtoSaved = characterMapper.convertToDTO(entitySaved, false);
        return dtoSaved;
    }
}
