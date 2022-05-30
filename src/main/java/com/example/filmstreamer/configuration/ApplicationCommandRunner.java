package com.example.filmstreamer.configuration;

import com.github.javafaker.Faker;
import com.example.filmstreamer.model.User;
import com.example.filmstreamer.service.UserService;
import com.example.filmstreamer.model.Movie;
import com.example.filmstreamer.service.MovieService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ApplicationCommandRunner implements CommandLineRunner {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //Scanner reader = new Scanner(System.in);
        createFakers();
    }

    public void createFakers(){
        logger.info("Welcome to the create movie Fakers");

        Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            String title = faker.book().title();
            int releaseYear = 1975 + (int)Math.round(Math.random() * (LocalDate.now().getYear() - 1975));
            int duration = 90 + (int)Math.round(Math.random() * (220 - 90));
            String directing = faker.artist().name();
            String casting = faker.artist().name() + ", " + faker.artist().name() + ", " + faker.artist().name() + ", " + faker.artist().name();
            String synopsis = faker.dune().quote();
            String genre = faker.book().genre();
            String ageRating =  String.valueOf(7 + (int)Math.round(Math.random() * (18 - 7)));
            Movie movie = new Movie(title, releaseYear, duration, directing, casting, synopsis, genre, ageRating);
            movieService.createMovie(movie);
        }

        logger.info("finishing create movie Fakers ...");

    }

}