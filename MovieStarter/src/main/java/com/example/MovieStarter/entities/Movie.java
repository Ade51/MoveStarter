package com.example.MovieStarter.entities;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Column(name = "story")
    private String story;

    @Column(name = "base64Img")
    private String base64Img;

    @Column(name = "languageId")
    private Integer languageId;

    @Column(name = "genreId")
    private Integer genreId;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Column(name = "createdTimestamp")
    private Date createdTimestamp;

    @Column(name = "lastUpdtTimestamp")
    private Date lastUpdtTimestamp;

    @Transient
    private String genre;

    @Transient
    private String language;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonUnwrapped
    private Rating rating;

    @Transient
    private List<Review> reviews;

}
