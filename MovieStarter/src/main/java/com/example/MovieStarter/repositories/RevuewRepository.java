package com.example.MovieStarter.repositories;

import com.example.MovieStarter.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevuewRepository extends JpaRepository<Review, Integer> {

}
