package com.example.filmstreamer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="Movie")
@Table(name="MOVIE_TABLE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID")
    private long movieId;
    @Column(name = "MOVIE_TITLE")
    private String title;
    @Column(name = "MOVIE_RELEASING")
    private int releaseYear;
    @Column(name = "MOVIE_DURATION")
    private int duration;
    @Column(name = "MOVIE_DIRECTING")
    private String directing;
    @Column(name = "MOVIE_CASTING")
    private String casting;
    @Column(name = "MOVIE_SYNOPSIS")
    private String synopsis;
    @Column(name = "MOVIE_GENRE")
    private String genre;
    @Column(name = "MOVIE_AGE_RATING")
    private String ageRating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<View> views = new ArrayList<>();

}
