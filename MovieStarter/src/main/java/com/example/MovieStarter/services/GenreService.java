package com.example.MovieStarter.services;

import com.example.MovieStarter.DTO.GenreDTO;
import com.example.MovieStarter.Errors.ErrorCodes;
import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.entities.Genre;
import com.example.MovieStarter.entities.Movie;
import com.example.MovieStarter.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    public ServiceResponseT<GenreDTO> addGenre(GenreDTO g) {

        Optional<Genre> gen = Optional.ofNullable(getGenreByName(g.getName()));

        if (gen.isPresent()) {
            return ServiceResponseT.createError(new ErrorMessage(HttpStatus.CONFLICT, "Genre aldready exist !", ErrorCodes.CannotAdd));
        }
        Genre genre = new Genre();
        genre.setName(g.getName());
        genreRepository.save(genre);
        return ServiceResponseT.forSuccess(g);
    }

    public Optional<Genre> findGenreById(Integer id) {
        return genreRepository.findById(id);
    }

    public ServiceResponse DeleteGenre(Integer genreId) {
        Optional<Genre> movie = findGenreById(genreId);

        if (movie.isPresent()) {
            genreRepository.deleteById(genreId);
            return ServiceResponse.forSuccess();
        } else
            return ServiceResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "The genre does not exists!", ErrorCodes.CannotDelete));
    }
}
