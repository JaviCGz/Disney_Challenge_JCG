package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    
    public CharacterServiceImpl(CharacterMapper characterMapper, CharacterRepository characterRepository) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
    }

    public CharacterDTO save(CharacterDTO dto) {
        Character entity = characterMapper.convertToEntity(dto);
        Character entitySaved = characterRepository.save(entity);
        return characterMapper.convertToDTO(entitySaved, false);
    }
    
    public List<BasicCharacterDTO> getAll() {
        List<Character> entities = characterRepository.findAll();
        
        return characterMapper.convertToBasicDTOList(entities);
    }
    
    public void delete(Long id) {
        characterRepository.deleteById(id);
    }
    
}
