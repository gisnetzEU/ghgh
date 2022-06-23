package com.example.filmstreamer.controller;

import com.example.filmstreamer.model.Movie;
import com.example.filmstreamer.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8086", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/movies")
public class MovieRestController {

    @Autowired
    MovieService movieservice;

    //here we are creating our end-point rest API
    //CRUD: read all movies
    @GetMapping("/movies")
    public ResponseEntity<Iterable<Movie>> getAllMovies() {
        Optional<Iterable<Movie>> moviesRetrieved = movieservice.getAllMovies();
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "movies");
        if (moviesRetrieved.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(moviesRetrieved.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: read, find one movie by id
    @GetMapping("/getMovie/{movieId}")
    public ResponseEntity<Movie> findMovieByIdPath(@PathVariable String movieId) {
        Optional<Movie> movieFound = movieservice.findMovieById(UUID.fromString(movieId));
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getMovie");
        if (movieFound.isPresent()) {
            headers.add("operationStatus", "found");
            return ResponseEntity.accepted().headers(headers).body(movieFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: delete movie by id
    @DeleteMapping("/deleteMovie/{movieId}")
    public ResponseEntity<Movie> deleteMoviePath(@PathVariable String movieId) {
        Optional<Movie> movieFound = movieservice.deleteMovieById(UUID.fromString(movieId));
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteMovie");
        if (movieFound.isPresent()) {
            headers.add("operationStatus", "deleted");
            return ResponseEntity.accepted().headers(headers).body(movieFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: read, find movies by title
    @GetMapping(path = "/getMoviesByTitle/{title}")
    public ResponseEntity<Iterable<Movie>> findMoviesByTitle(@PathVariable String title) {
        Optional<Iterable<Movie>> moviesFound = movieservice.findMoviesByTitle(title);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getMoviesByTitle");

        if (moviesFound.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(moviesFound.get());
        } else {
            headers.add("operationStatus", "not Found");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: create movie
    @PostMapping(path = "/createMovie", consumes = "application/json")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Optional<Movie> movieCreated = movieservice.createMovie(movie);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "createMovie");

        if (movieCreated.isPresent()) {
            headers.add("operationStatus", "created");
            return ResponseEntity.accepted().headers(headers).body(movieCreated.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: update movie
    @PostMapping(path = "/updateMovie", consumes = "application/json")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        Optional<Movie> movieFound = movieservice.findMovieById(movie.getMovieUUID());
        Optional<Movie> movieUpdate = movieFound;

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateMovie");

        if (!movieFound.isPresent()) {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else if (movie.equals(movieFound.get())) {
            headers.add("operationStatus", "no data to update");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            Boolean mustUpdate = false;
            if (!movie.getTitle().equals(movieFound.get().getTitle()) && !movie.getTitle().equals("")) {
                movieUpdate.get().setTitle(movie.getTitle());
                headers.add("title", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getCasting().equals(movieFound.get().getCasting()) && !movie.getCasting().equals("")) {
                movieUpdate.get().setCasting(movie.getCasting());
                headers.add("casting", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getDirecting().equals(movieFound.get().getDirecting()) && !movie.getDirecting().equals("")) {
                movieUpdate.get().setDirecting(movie.getDirecting());
                headers.add("directing", "to be updated");
                mustUpdate = true;
            }
            if ((movie.getReleaseYear() != movieFound.get().getReleaseYear()) && (movie.getReleaseYear() != 0)) {
                movieUpdate.get().setReleaseYear(movie.getReleaseYear());
                headers.add("release year", "to be updated");
                mustUpdate = true;
            }
            if ((movie.getDuration() != movieFound.get().getDuration()) && (movie.getDuration() != 0)) {
                movieUpdate.get().setDuration(movie.getDuration());
                headers.add("duration", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getSynopsis().equals(movieFound.get().getSynopsis()) && !movie.getSynopsis().equals("")) {
                movieUpdate.get().setSynopsis(movie.getSynopsis());
                headers.add("synopsis", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getGenre().equals(movieFound.get().getGenre()) && !movie.getGenre().equals("")) {
                movieUpdate.get().setGenre(movie.getGenre());
                headers.add("genre", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getAgeRating().equals(movieFound.get().getAgeRating()) && !movie.getAgeRating().equals("")) {
                movieUpdate.get().setAgeRating(movie.getAgeRating());
                headers.add("age rating", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getPosterPath().equals(movieFound.get().getPosterPath()) && !movie.getPosterPath().equals("")) {
                movieUpdate.get().setPosterPath(movie.getPosterPath());
                headers.add("poster path", "to be updated");
                mustUpdate = true;
            }
            if (!movie.getVideoPath().equals(movieFound.get().getVideoPath()) && !movie.getVideoPath().equals("")) {
                movieUpdate.get().setVideoPath(movie.getVideoPath());
                headers.add("video path", "to be updated");
                mustUpdate = true;
            }
            if (mustUpdate) {
                headers.add("operationStatus", "updated");
                return ResponseEntity.accepted().headers(headers).body(movieservice.updateMovie(movieUpdate.get()).get());
            } else {
                headers.add("operationStatus", "no valid data to update");
                return ResponseEntity.accepted().headers(headers).body(null);
            }
        }
    }


}
