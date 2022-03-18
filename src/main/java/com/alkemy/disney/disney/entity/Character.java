package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disney_character")
@Getter
@Setter
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String image;
    private String name;
    private int age;
    private double weight;
    private String story;
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();
}
