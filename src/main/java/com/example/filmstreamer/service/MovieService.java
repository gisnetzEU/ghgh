package com.example.filmstreamer.service;

import java.util.Optional;

import com.example.filmstreamer.model.Movie;
import com.example.filmstreamer.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Optional<Iterable<Movie>> getAllMovies() {
        return Optional.of(movieRepository.findAll());
    }

    public Optional<Movie> createMovie(Movie movie){
        return Optional.of(movieRepository.save(movie));
    }

    public Optional<Movie> findMovieById(Long id){
        return movieRepository.findById(id);
    }

    public Optional<Movie> deleteMovieById(Long id){
        //Find out IF this id-movie IS in our DB
        Optional<Movie> movieFound = movieRepository.findById(id);
        if(movieFound.isPresent()) {
            movieRepository.deleteById(id);
            return Optional.of(movieFound.get());
        } else {
            return null;
        }
    }

    public Optional<Movie> updateMovie(Movie movie) {
        Optional<Movie> movieFound = movieRepository.findById(movie.getMovieId());
        if(movieFound.isPresent()) {
            return Optional.of(movieRepository.save(movie));
        } else {
            return null;
        }
    }

    public Optional<Iterable<Movie>> findMoviesByTitle(String title){
        return movieRepository.findMoviesByTitle(title);
    }

    public Optional<Movie> deleteMovieByTitle(String title){
        return movieRepository.deleteMovieByTitle(title);
    }

    public int count() {
        return (int) movieRepository.count();
    }

}