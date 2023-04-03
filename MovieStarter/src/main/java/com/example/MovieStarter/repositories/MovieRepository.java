package com.example.MovieStarter.repositories;

import com.example.MovieStarter.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findMovieByName(String name);
}
