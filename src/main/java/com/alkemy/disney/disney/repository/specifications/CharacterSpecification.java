package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class CharacterSpecification {
    
    public Specification<Character> getByFilters(CharacterFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            
            List<Predicate> predicates = new ArrayList<>();
        
            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
    
            if (filtersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal((root.get("age")),
                                filtersDTO.getAge()
                        )
                );
            }
            
            if (!CollectionUtils.isEmpty(filtersDTO.getMoviesId())) {
                Join<Movie, Character> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMoviesId()));
            }
        
            query.distinct(true);
        
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
