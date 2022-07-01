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
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="View")
@Table(name="VIEW_TABLE")
public class View implements Serializable {

    @Id
    @GeneratedValue (generator = "uuid2")
    @GenericGenerator (name = "uuid2", strategy = "uuid2")
    @Column (name = "VIEW_UUID", columnDefinition = "BINARY (16)")
    private UUID viewUUID;
    @Column(name = "VIEW_DATETIME")
    private LocalDateTime viewDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USER_FK", referencedColumnName="USER_UUID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="MOVIE_FK", referencedColumnName="MOVIE_UUID")
    private Movie movie;

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

    public View(User user, Movie movie) {
        this.viewDateTime = LocalDateTime.now();
        this.user = user;
        this.movie = movie;
    }

}
