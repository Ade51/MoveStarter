package com.example.MovieStarter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reviews")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer reviewId;

    @Column(name = "movieId")
    private Integer movieId;

    @Column(name = "createdUserId")
    private Integer createdUserId;

    @Column(name = "createdUserName")
    private String createdUserName;

    @Column(name = "likeMovie")
    private String likeMovie;

    @Column(name = "comments")
    private String comments;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "createTimestamp")
    private Date createTimestamp;

}
