package com.example.MovieStarter.repositories;

import com.example.MovieStarter.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    Director findDiretorByName(String name);
}
