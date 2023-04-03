package com.example.MovieStarter.DTO;

import lombok.Getter;

@Getter
public class ReviewDTO {
    private Integer createdUserId;

    private String createdUserName;

    private String likeMovie;

    private String comments;

    private Double rating;
}
