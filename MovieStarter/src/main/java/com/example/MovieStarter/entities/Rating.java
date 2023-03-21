package com.example.MovieStarter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "movieId")
    private Integer movieId;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislike")
    private Integer dislike;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "totalRatings")
    private Integer totalRatings;

    @Column(name = "createTimestamp")
    private Date createTimestamp;

}

