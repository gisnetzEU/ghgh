package com.example.filmstreamer.configuration;

import com.github.javafaker.Faker;
import com.example.filmstreamer.model.User;
import com.example.filmstreamer.service.UserService;
import com.example.filmstreamer.model.Movie;
import com.example.filmstreamer.service.MovieService;
import com.example.filmstreamer.model.View;
import com.example.filmstreamer.service.ViewService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ApplicationCommandRunner implements CommandLineRunner {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;
    @Autowired
    ViewService viewService;

    @Override
    public void run(String... args) throws Exception {
        //Scanner reader = new Scanner(System.in);

        // Faker DB date creator block

        Faker faker = new Faker();
        createMoviesFaker(faker);
        createUsersFaker(faker);
        createViewsFaker(faker);

    }

    public void createMoviesFaker(Faker faker){
        logger.info("Welcome to the create movies Faker");
        for (int i = 1; i <= 100; i++) {
            String title = faker.book().title();
            int releaseYear = 1975 + (int)Math.round(Math.random() * (LocalDate.now().getYear() - 1975));
            int duration = 90 + (int)Math.round(Math.random() * (220 - 90));
            String directing = faker.artist().name();
            String casting = faker.artist().name() + ", " + faker.artist().name() + ", " + faker.artist().name() + ", " + faker.artist().name();
            String synopsis = faker.dune().quote();
            String genre = faker.book().genre();
            String ageRating =  String.valueOf(7 + (int)Math.round(Math.random() * (18 - 7)));
            String posterPath = "test.jpg";
            String videoPath = "test.avi";
            Movie movie = new Movie(title, releaseYear, duration, directing, casting, synopsis, genre, ageRating, posterPath, videoPath);
            movieService.createMovie(movie);
        }
        logger.info("finishing create movies Faker ...");
    }

    public void createUsersFaker(Faker faker){
        logger.info("Welcome to the create users Faker");
        for (int i = 1; i <= 100; i++) {
            String[] name = faker.book().author().split(" ");
            String userFirstName = name[0];
            String userLastName = name[1];
            LocalDate birthDate = LocalDate.now();
            String userEmail = userLastName + "@movie.edu";
            int password = 1234;
            String userRol = "user";
            User user = new User(userEmail, password, userRol, userFirstName, userLastName, birthDate);
            userService.createUser(user);
        }
        logger.info("finishing create users Faker ...");
    }

    private void createViewsFaker(Faker faker) {
        logger.info("Welcome to the create views Faker");
        for (int i = 1; i <= 100; i++) {
            Movie movie = movieService.findMovieById((long)i).get();
            User user = userService.findUserById((long)i+100).get();

            View view = new View(user, movie);
            viewService.createView(view);
        }
        logger.info("finishing create views Faker ...");
    }


}