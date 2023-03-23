package com.example.MovieStarter.repositories;

import com.example.MovieStarter.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByMovieId(Integer movieId);
}
