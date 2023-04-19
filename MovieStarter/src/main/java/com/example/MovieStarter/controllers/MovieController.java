package com.example.MovieStarter.controllers;

import com.example.MovieStarter.DTO.MovieDTO;
import com.example.MovieStarter.DTO.ReviewDTO;
import com.example.MovieStarter.Errors.ErrorCodes;
import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.Responses.RequestResponse;
import com.example.MovieStarter.Responses.RequestResponseT;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.entities.Movie;
import com.example.MovieStarter.entities.Review;
import com.example.MovieStarter.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService moviesService;

    @RequestMapping(value = "/popular", method = RequestMethod.GET)
    public RequestResponse getPopularMovies(Model model) throws NullPointerException {

        List<Movie> list = moviesService.getAllMovies();
        if (list.isEmpty()) {
            return RequestResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "No movies found!", ErrorCodes.EntityNotFound));
        } else {
            try {
                list.sort((o1, o2)
                        -> o1.getRating().getRating().compareTo(
                        o2.getRating().getRating()));
                Collections.reverse(list);

                model.addAttribute("Popular_list", list);
                return RequestResponseT.okRequestResponse();
            } catch (NullPointerException e) {
                model.addAttribute("Popular_list", list);
                return RequestResponseT.okRequestResponse();
            }
        }
    }

    @PutMapping("/{movie_id}/addLanguage/{language_id}")
    public RequestResponse addLanguageToMovie(@PathVariable Integer movie_id, @PathVariable Integer language_id) {

        ServiceResponse response =  moviesService.addLanguageToMovie(movie_id, language_id);
        return RequestResponseT.fromError(response.getError());
    }
    @PutMapping("/{movie_id}/addGenre/{genre_id}")
    public RequestResponse addGenreToMovie(@PathVariable Integer movie_id, @PathVariable Integer genre_id) {

        ServiceResponse response =  moviesService.addGenreToMovie(movie_id, genre_id);
        return RequestResponseT.fromError(response.getError());
    }

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public RequestResponse<Movie> getMovieInfo(@PathVariable("movieId") Integer movieId) {
        ServiceResponseT<Movie> response =  moviesService.getMovieInfo(movieId);
        return RequestResponse.fromServiceResponseOfType(response);
    }

    @PostMapping("/review/{movie_id}")
    public RequestResponse<ReviewDTO> addMovieReview(@RequestBody ReviewDTO reviewDTO,
                                                     @PathVariable Integer movie_id, Model model) {
        LOG.info("Add Movie Reviews");
        model.addAttribute("new_review", moviesService.getMovieInfo(movie_id));
        ServiceResponseT<ReviewDTO> response = moviesService.addReview(reviewDTO, movie_id);

        return RequestResponse.fromServiceResponseOfType(response);
    }

    @GetMapping
    public RequestResponse getAllMovies(Model model) {
        var list = moviesService.getAllMovies();

        if (list.isEmpty()) {
            return RequestResponseT.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "No movies found!", ErrorCodes.PhysicalFileNotFound));
        }
        model.addAttribute("MovieList", moviesService.getAllMovies());
        return RequestResponseT.okRequestResponse();
    }

    @PostMapping("/add/{user_id}")
    public RequestResponse<MovieDTO> addMovie(@PathVariable Integer user_id, @RequestBody MovieDTO movie, Model model) {


        ServiceResponseT<MovieDTO> response = moviesService.addMovie(movie, user_id);
        model.addAttribute("MovieList", moviesService.getAllMovies());
        return RequestResponse.fromServiceResponseOfType(response);
    }

    @DeleteMapping("/del/{movie_id}/{user_id}")
    public RequestResponse DeleteMovie(@PathVariable Integer movie_id, @PathVariable Integer user_id) {

        ServiceResponse response = moviesService.DeleteMovie(movie_id, user_id);
        if (response.isOk()) {
            return RequestResponseT.okRequestResponse();
        }
        return new RequestResponseT("Failed to delete movie", response.getError());
    }
}
