package com.example.MovieStarter.services;

import com.example.MovieStarter.DTO.MovieDTO;
import com.example.MovieStarter.DTO.ReviewDTO;
import com.example.MovieStarter.Errors.ErrorCodes;
import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.entities.*;
import com.example.MovieStarter.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private GenreRepository genreRepository;


    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<Movie> getAllMovies() {
        List<Movie> list = movieRepository.findAll();
        return list;
    }

    public ServiceResponseT<MovieDTO> addMovie(MovieDTO movie) {

        Optional<Movie> mov = Optional.ofNullable(movieRepository.findMovieByName(movie.getName()));
        if (mov.isPresent()) {
            return ServiceResponseT.createError(new ErrorMessage(HttpStatus.CONFLICT, "The movie already exists!", ErrorCodes.CannotAdd));
        }
        Movie new_movie = new Movie();
        new_movie.setCreatedTimestamp(new Date());
        new_movie.setLastUpdtTimestamp(new Date());
        new_movie.setRating(null);
        new_movie.setName(movie.getName());
        new_movie.setReleaseYear(movie.getReleaseYear());
        new_movie.setStory(movie.getStory());
        movieRepository.save(new_movie);

        return ServiceResponseT.forSuccess(movie);
    }

    public List<Movie> getMovieInfo(Integer movieId) {

        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        Movie movie = movieOptional.get();
        // Update Reviews
        Set<Review> reviews = reviewRepository.findAllByMovieId(movie.getId());
        movie.setReviews(reviews);

        // Update Genre & Language
        List<Movie> list = List.of(movie);
        updateLanguageGenres(list);

        return list;
    }

    private void updateLanguageGenres(List<Movie> list) {
        Map<Integer, String> languageMap = new HashMap<>();
        Map<Integer, String> genreMap = new HashMap<>();

        List<Language> languageList = getAllLanguages();
        languageList.parallelStream().forEach(obj -> languageMap.put(obj.getId(), obj.getName()));

        List<Genre> genreList = getAllGenres();
        genreList.parallelStream().forEach(obj -> genreMap.put(obj.getId(), obj.getName()));


    }

    public ServiceResponseT<ReviewDTO> addReview(ReviewDTO reviews, Integer movie_id) {
        Review review = new Review();
        Optional<Movie> mov = movieRepository.findById(movie_id);
        Movie movie = mov.get();

        review.setMovie(movie);
        review.setCreateTimestamp(new Date());
        review.setComments(review.getComments());
        review.setRating(reviews.getRating());
        review.setLikeMovie(reviews.getLikeMovie());
        review.setCreatedUserId(reviews.getCreatedUserId());

        reviewRepository.save(review);

        movie.getReviews().add(review);

        Rating rating = null;
        List<Rating> ratingList = ratingRepository.findAllByMovieId(movie_id);
        if (!ratingList.isEmpty()) {
            LOG.info("Update existing rating");
            Collections.reverse(ratingList);
            rating = ratingList.get(0);
            rating.setRating((rating.getRating() + reviews.getRating()) / 2);
        } else {
            LOG.info("Add a new rating");
            rating = new Rating();
            rating.setMovie(movie);
            rating.setRating(reviews.getRating());
            rating.setLikes(0);
            rating.setCreateTimestamp(new Date());
            rating.setDislike(0);
            rating.setLikes(0);
            rating.setTotalRatings(0);
        }

        if ("Y".equalsIgnoreCase(reviews.getLikeMovie())) {
            rating.setLikes(rating.getLikes() + 1);
        } else {
            rating.setDislike(rating.getDislike() + 1);
        }
        rating.setTotalRatings(rating.getTotalRatings() + 1);
        movie.setRating(rating);
        ratingRepository.save(rating);
        return ServiceResponseT.forSuccess(reviews);
    }

    public Optional getMovieById(Integer movieId) {
        return movieRepository.findById(movieId);
    }

    public ServiceResponse DeleteMovie(Integer movie_id) {

        Optional<Movie> movie = getMovieById(movie_id);

        if (movie.isPresent()) {
            movieRepository.deleteById(movie_id);
            return ServiceResponse.forSuccess();
        } else
            return ServiceResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "The movie does not exists!", ErrorCodes.CannotDelete));
    }

    public ServiceResponse addGenreToMovie(Integer movieId, Integer genreId) {
        Optional<Movie> movie = getMovieById(movieId);
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (movie.isPresent() && genre.isPresent()) {
            Movie mov = movie.get();
            Genre gen = genre.get();
            mov.getGenres().add(gen);
            gen.setMovie(mov);
            movieRepository.save(mov);
            genreRepository.save(gen);
            return ServiceResponse.forSuccess();
        }
        return ServiceResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "Either Movie or Genre does not exist !", ErrorCodes.CannotAdd));
    }

    public ServiceResponse addLanguageToMovie(Integer movieId, Integer languageId) {
        Optional<Movie> movie = getMovieById(movieId);
        Optional<Language> language = languageRepository.findById(languageId);
        if (movie.isPresent() && language.isPresent()) {
            Movie mov = movie.get();
            Language lan = language.get();
            mov.getLanguages().add(lan);
            lan.setMovie(mov);
            movieRepository.save(mov);
            languageRepository.save(lan);
            return ServiceResponse.forSuccess();
        }
        return ServiceResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "Either Movie or Language does not exist !", ErrorCodes.CannotAdd));
    }
}
