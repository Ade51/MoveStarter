package com.example.MovieStarter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Directors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(name = "directed_films",
            joinColumns = @JoinColumn(name = "directors_id"),
            inverseJoinColumns = @JoinColumn(name = "movies_id"))
    private Set<Movie> directed_films = new HashSet<>();

    public void saveMovies(Movie mov) {
        directed_films.add(mov);
    }
}
