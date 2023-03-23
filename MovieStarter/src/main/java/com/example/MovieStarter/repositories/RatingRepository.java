package com.example.MovieStarter.repositories;

import com.example.MovieStarter.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findAllByMovieId(Integer movieId);
}
