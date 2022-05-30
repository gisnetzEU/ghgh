package com.example.filmstreamer.repository;

import com.example.filmstreamer.model.Movie;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> deleteMovieByTitle(String title);

    Optional<Iterable<Movie>> findMoviesByTitle(String title);
}
