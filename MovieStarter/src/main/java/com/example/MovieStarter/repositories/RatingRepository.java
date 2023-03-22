package com.example.MovieStarter.repositories;

import com.example.MovieStarter.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
