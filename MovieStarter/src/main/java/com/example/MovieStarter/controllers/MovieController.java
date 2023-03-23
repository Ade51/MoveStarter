package com.example.MovieStarter.controllers;

import com.example.MovieStarter.entities.Genre;
import com.example.MovieStarter.entities.Language;
import com.example.MovieStarter.entities.Movie;
import com.example.MovieStarter.entities.Review;
import com.example.MovieStarter.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService moviesService;

    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public List<Language> getAllLanguages() {
        return moviesService.getAllLanguages();
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public List<Genre> getAllGenres() {
        return moviesService.getAllGenres();
    }

    @RequestMapping(value = "/popular", method = RequestMethod.GET)
    public List<Movie> getPopularMovies() {
        LOG.info("Fetch Popular Movies...");
        List<Movie> list = moviesService.getAllMovies();
        LOG.debug(": " + list.size());
        List<Movie> ratedMovies = list.parallelStream().filter(obj -> null != obj.getRating())
                .collect(Collectors.toList());
        LOG.debug(": " + ratedMovies.size());
        return ratedMovies;
    }

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public List<Movie> getMovieInfo(@PathVariable("movieId") Integer movieId) {
        return moviesService.getMovieInfo(movieId);
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public List<Movie> addMovieReview(@RequestBody Review reviews) {
        LOG.info("Add Movie Reviews...");
        moviesService.addReview(reviews);

        return moviesService.getMovieInfo(reviews.getMovieId());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        LOG.info("Fetch all the Movies...");
        return moviesService.getAllMovies();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Movie> addMovie(@RequestBody Movie movie) {
        LOG.info("Add a Movie...");
        moviesService.addMovie(movie);
        return moviesService.getAllMovies();
    }
}
