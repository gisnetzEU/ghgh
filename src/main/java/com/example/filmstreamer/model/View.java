package com.example.filmstreamer.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;


@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="View")
@Table(name="VIEW_TABLE")
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VIEW_ID")
    private long viewId;
    @Column(name = "VIEW_DATETIME")
    private LocalTime viewDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_FK")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_FK")
    private Movie movie;

    public View(User user, Movie movie) {
        this.viewDateTime = LocalTime.now();
        this.user = user;
        this.movie = movie;
    }
}
