package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.BasicCharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<BasicCharacterDTO>> getAll() {
        List<BasicCharacterDTO> characters = characterService.getAll();

        return ResponseEntity.ok().body(characters);
    }
    
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO savedCharacter = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
