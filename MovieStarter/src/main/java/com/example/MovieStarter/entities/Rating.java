package com.example.MovieStarter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ratings")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer likes;

    private Integer dislike;

    private Double rating;

    private Integer totalRatings;

    private Date createTimestamp;

    @OneToOne
    @MapsId
    @JoinColumn(name = "movie_id")
    private Movie movie;
}

