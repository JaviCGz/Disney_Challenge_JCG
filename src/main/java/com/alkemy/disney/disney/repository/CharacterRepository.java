package com.alkemy.disney.disney.repository;
import com.alkemy.disney.disney.entity.Character;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    /**
     * Method used to search for characters(DTO) matching a given specification.
     * @param spec: Search specifications
     */
    List<Character> findAll(Specification<Character> spec);
}
