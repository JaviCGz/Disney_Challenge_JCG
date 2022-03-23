package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.BasicMovieDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    @GetMapping
    public ResponseEntity<List<BasicMovieDTO>> getAll() {
        List<BasicMovieDTO> movies = movieService.getAll();
    
        return ResponseEntity.ok().body(movies);
    }
    
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto) {
        MovieDTO savedMovie = movieService.save(dto);
    
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto) {
        MovieDTO updatedMovie = movieService.update(id, dto);
    
        return ResponseEntity.ok(updatedMovie);
    }
    
    /*TODO: Ya están listas las opciones para obtener, actualizar y eliminar las entidades.
       Falta por hacer:
       Agregar las validaciones de los dto recibidos para que no llegue ningún campo vacío
       Agregar las excepciones correspondientes
       Agregar get por filtros
       Agregar get de detalles
       Después de eso ya puedo continuar por agregar SendGrid*/
}
