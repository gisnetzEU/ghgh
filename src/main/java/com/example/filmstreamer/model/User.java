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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor

@Entity(name="User")
@Table(name="USER_TABLE")

public class User implements Serializable {

    @Id
    @GeneratedValue (generator = "uuid2")
    @GenericGenerator (name = "uuid2", strategy = "uuid2")
    @Column (name = "USER_UUID", columnDefinition = "BINARY (16)")
    private UUID userUUID;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @JsonIgnore
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_ROL")
    private String userRol;
    @Column(name = "USER_FIRSTNAME")
    private String userFirstName;
    @Column(name = "USER_LASTNAME")
    private String userLastName;
    @Column(name = "USER_BIRTHDATE")
    private LocalDate birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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

    public User(String userEmail, String password, String userRol,
                String userFirstName, String userLastName, LocalDate birthDate) {
        this.userEmail = userEmail;
        this.password = password;
        this.userRol = userRol;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.birthDate = birthDate;
    }

    public void addView(View view) {
        this.views.add(view);
        view.setUser(this);
    }

}
