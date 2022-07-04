package com.example.filmstreamer.repository;

import com.example.filmstreamer.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends CrudRepository<Movie, UUID> {

    Optional<Movie> deleteMovieByTitleIgnoreCase(String title);

    Optional<Iterable<Movie>> findMoviesByTitleIgnoreCase(String title);

    Optional<Iterable<Movie>> findMoviesByTitleContainingIgnoreCase(String titlePart);

}
