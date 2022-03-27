package com.alkemy.disney.disney.service.implementations;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.exception.InvalidDTOException;
import com.alkemy.disney.disney.exception.NotFoundException;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {
    
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final CharacterSpecification characterSpecification;
    
    public CharacterServiceImpl(CharacterMapper characterMapper, CharacterRepository characterRepository,
                                CharacterSpecification characterSpecification) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
    }

    public CharacterDTO save(CharacterDTO dto) {
        
        validateReceivedDTO(dto, true);
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
        
        validateReceivedDTO(dto, false);
        Optional<Character> entity = characterRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Character ID. not found");
        }
        characterMapper.refreshValues(entity.get(), dto);
        Character savedEntity = characterRepository.save(entity.get());
    
        return characterMapper.convertToDTO(savedEntity, false);
    }
    
    public CharacterDTO getDetails(Long id) {
        Optional<Character> result = characterRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Character ID. not found");
        }
        return characterMapper.convertToDTO(result.get(), true);
    }
    
    public List<CharacterDTO> getByFilters(String name, Integer age, List<Long> moviesId) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, moviesId);
        List<Character> entities = characterRepository.findAll(
                characterSpecification.getByFilters(filtersDTO)
        );
        return this.characterMapper.convertToDTOList(entities, true);
    }
    
    public List<CharacterDTO> convertToDTOList (List<Character> entityList, boolean loadMovies) {
        
        return characterMapper.convertToDTOList(entityList, loadMovies);
    }
    
    public List<Character> lookForOrCreate(List<CharacterDTO> dtoList) {
        
        List<Character> entityList = new ArrayList<>();
        for(CharacterDTO dto : dtoList) {
            if (dto.getId() != null) {
                Optional<Character> result = characterRepository.findById(dto.getId());
                if (result.isPresent()) {
                    entityList.add(result.get());
                } else {
                    throw new NotFoundException("A non-existent ID. was received." +
                            "New characters must not have an user-assign ID.");
                }
            } else {
                validateReceivedDTO(dto, false);
                entityList.add(characterMapper.convertToEntity(dto));
            }
        }
        
        return entityList;
    }
    
    private void validateReceivedDTO(CharacterDTO dto, boolean extraValidation) {
        
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
        if (extraValidation) {
            if (dto.getMovies() == null || dto.getMovies().isEmpty()) {
                throw new InvalidDTOException("Character must be in at least one movie");
            }
        }
    }
    
}
