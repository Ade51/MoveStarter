package com.example.MovieStarter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    private Integer releaseYear;

    private String story;

    private String base64Img;

    @OneToMany(mappedBy = "movie")
    private Set<Language> languages = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Genre> genres = new HashSet<>();

    private Date createdTimestamp;

    private Date lastUpdtTimestamp;

    @OneToOne(mappedBy = "movie")
    @PrimaryKeyJoinColumn
    private Rating rating;

    @ManyToMany(mappedBy = "directed_films")
    @JsonIgnore
    private Set<Director> directors = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews = new HashSet<>();

}
