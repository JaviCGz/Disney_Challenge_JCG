package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {
    
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    
    public CharacterServiceImpl(CharacterMapper characterMapper, CharacterRepository characterRepository) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
    }

    public CharacterDTO save(CharacterDTO dto) {
        
        validateReceivedDTO(dto);
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
    
    public CharacterDTO update(Long id, CharacterDTO dto) {
        
        validateReceivedDTO(dto);
        Optional<Character> entity = characterRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ParamNotFound("Character ID. not found in database");
        }
        characterMapper.refreshValues(entity.get(), dto);
        Character savedEntity = characterRepository.save(entity.get());
    
        return characterMapper.convertToDTO(savedEntity, false);
    }
    
    private void validateReceivedDTO(CharacterDTO dto) {
        if (dto == null) {
            throw new InvalidDTOException("No character was received");
        }
        if (dto.getImage() == null || dto.getImage().isBlank()) {
            throw new InvalidDTOException("Character must have an image");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidDTOException("Character must have a name");
        }
        if (dto.getAge() == null) {
            throw new InvalidDTOException("Character must have an age");
        }
        if (dto.getWeight() == null) {
            throw new InvalidDTOException("Character must have a weight");
        }
        if (dto.getStory() == null || dto.getStory().isBlank()) {
            throw new InvalidDTOException("Character must have a story");
        }
    }
    
}
