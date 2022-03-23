package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.BasicGenreDTO;
import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<List<BasicGenreDTO>> getAll() {
        List<BasicGenreDTO> genres = genreService.getAll();

        return ResponseEntity.ok().body(genres);
    }
    
    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO dto) {
        GenreDTO savedGenre = genreService.save(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO dto) {
        GenreDTO updatedGenre = genreService.update(id, dto);
    
        return ResponseEntity.ok(updatedGenre);
    }
    
}
