package com.example.MovieStarter.services;

import com.example.MovieStarter.DTO.DirectorDTO;
import com.example.MovieStarter.Errors.ErrorCodes;
import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.entities.Director;
import com.example.MovieStarter.entities.Movie;
import com.example.MovieStarter.repositories.DirectorRepository;
import com.example.MovieStarter.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Director> getDirectorById(Integer directorid) {
        return directorRepository.findById(directorid);
    }


    public ServiceResponse addMovies(Integer directorid, Integer movieid) {
        Optional<Director> director = getDirectorById(directorid);
        Optional<Movie> movie = movieRepository.findById(movieid);

        if (movie.isPresent() && director.isPresent()) {
            Movie mov = movie.get();
            Director dir = director.get();
            dir.saveMovies(mov);
            mov.saveDirector(dir);
            return ServiceResponse.forSuccess();
        }
        return ServiceResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "Either director or movie does not exist !", ErrorCodes.CannotAdd));
    }

    public ServiceResponseT<DirectorDTO> addDirector(DirectorDTO director) {
        Optional<Director> dir = Optional.ofNullable(directorRepository.findDiretorByName(director.getName()));

        if (dir.isPresent()) {
            return ServiceResponseT.createError(new ErrorMessage(HttpStatus.CONFLICT, "The Director already exists!", ErrorCodes.CannotAdd));
        } else {
            Director new_director = new Director();
            new_director.setName(director.getName());
            directorRepository.save(new_director);
            return ServiceResponseT.forSuccess(director);
        }
    }

    public ServiceResponse DeleteDirector(Integer directorId) {
        Optional<Director> director = getDirectorById(directorId);

        if (director.isPresent()) {
            directorRepository.deleteById(directorId);
            return ServiceResponse.forSuccess();
        } else
            return ServiceResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "The director does not exist!", ErrorCodes.CannotDelete));
    }
}
