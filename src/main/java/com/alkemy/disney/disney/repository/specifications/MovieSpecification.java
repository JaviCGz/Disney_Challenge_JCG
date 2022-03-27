package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;
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
public class MovieSpecification {
    
    public Specification<Movie> getByFilters(MovieFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
    
            List<Predicate> predicates = new ArrayList<>();
    
            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }
    
            if (!CollectionUtils.isEmpty(filtersDTO.getGenresId())) {
                Join<Movie, Character> join = root.join("genres", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(filtersDTO.getGenresId()));
            }
    
            query.distinct(true);
            
            String orderByAttribute = "creationDate";
            
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByAttribute)) :
                            criteriaBuilder.desc(root.get(orderByAttribute))
            );
    
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
