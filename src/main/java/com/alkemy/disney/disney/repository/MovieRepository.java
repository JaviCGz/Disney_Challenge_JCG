package com.alkemy.disney.disney.repository;
import com.alkemy.disney.disney.entity.Movie;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    /**
     * Method used to search for movies(DTO) matching a given specification.
     * @param spec: Search specifications
     */
    List<Movie> findAll(Specification<Movie> spec);
}
