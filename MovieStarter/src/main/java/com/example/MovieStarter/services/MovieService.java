package com.example.MovieStarter.services;

import com.example.MovieStarter.entities.*;
import com.example.MovieStarter.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private MovieRepository movieRepository;
    private RatingRepository ratingRepository;
    private ReviewRepository reviewRepository;
    private GenreRepository genreRepository;
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<Movie> getAllMovies() {
        List<Movie> list = movieRepository.findAll();
        updateLanguageGenres(list);
        return list;
    }

    public void addMovie(Movie movie) {
        movie.setCreatedTimestamp(new Date());
        movie.setLastUpdtTimestamp(new Date());
        movie.setRating(null);
        movieRepository.save(movie);
    }

    public List<Movie> getMovieInfo(Integer movieId) {

        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        Movie movie = movieOptional.get();
        // Update Reviews
        List<Review> reviews = reviewRepository.findAllByMovieId(movie.getId());
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

        list.parallelStream().forEach(obj -> {
            obj.setLanguage(languageMap.get(obj.getLanguageId()));
            obj.setGenre(genreMap.get(obj.getGenreId()));
        });

    }

    public void addReview(Review reviews) {
        reviews.setCreateTimestamp(new Date());
        reviewRepository.save(reviews);
        Rating ratings = null;
        List<Rating> ratingList = ratingRepository.findAllByMovieId(reviews.getMovieId());
        if (!ratingList.isEmpty()) {
            LOG.info("Update existing ratings...");
            ratings = ratingList.get(0);
        } else {
            LOG.info("Add a new ratings...");
            ratings = new Rating(reviews.getMovieId(), 0, 0, (double) 0, 0, new Date());
        }

        if ("Y".equalsIgnoreCase(reviews.getLikeMovie())) {
            ratings.setLikes(ratings.getLikes() + 1);
        } else {
            ratings.setDislike(ratings.getDislike() + 1);
        }
        ratings.setTotalRatings(ratings.getTotalRatings() + 1);
        ratingRepository.save(ratings);
    }

}
