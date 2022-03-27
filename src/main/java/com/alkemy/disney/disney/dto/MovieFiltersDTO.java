package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieFiltersDTO {
    private String title;
    private List<Long> genresId;
    private String order;
    
    public MovieFiltersDTO(String title, List<Long> genresId, String order) {
        this.title = title;
        this.genresId = genresId;
        this.order = order;
    }
    
    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }
    
    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }
}
