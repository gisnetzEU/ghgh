package com.example.filmstreamer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor

@Entity(name="Movie")
@Table(name="MOVIE_TABLE")

public class Movie implements Serializable {

    @Id
    @GeneratedValue (generator = "uuid2")
    @GenericGenerator (name = "uuid2", strategy = "uuid2")
    @Column (name = "MOVIE_UUID", columnDefinition = "BINARY (16)")
    private UUID movieUUID;
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
    @Column(name = "MOVIE_SYNOPSIS", columnDefinition="TEXT")
    private String synopsis;
    @Column(name = "MOVIE_GENRE")
    private String genre;
    @Column(name = "MOVIE_AGE_RATING")
    private String ageRating;
    @Column(name = "POSTER_PATH")
    private String posterPath;
    @Column(name = "VIDEO_PATH")
    private String videoPath;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<View> views = new ArrayList<>();

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private Instant createdAt;
    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private Instant updatedAt;
    @JsonIgnore
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @JsonIgnore
    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @JsonIgnore
    @Version
    @Column(name = "VERSION")
    Long version;

    public Movie(String title, int releaseYear, int duration, String directing,
                 String casting, String synopsis, String genre, String ageRating,
                 String posterPath, String videoPath) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.directing = directing;
        this.casting = casting;
        this.synopsis = synopsis;
        this.genre = genre;
        this.ageRating = ageRating;
        this.posterPath = posterPath;
        this.videoPath = videoPath;
    }

    public void addView(View view) {
        this.views.add(view);
        view.setMovie(this);
    }
}
