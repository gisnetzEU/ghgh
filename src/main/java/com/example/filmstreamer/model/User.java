package com.example.filmstreamer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
@Entity(name="User")
@Table(name="USER_TABLE")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "USER_PASSWORD")
    private int password;
    @Column(name = "USER_ROL")
    private String userRol;
    @Column(name = "USER_FIRSTNAME")
    private String userFirstName;
    @Column(name = "USER_LASTNAME")
    private String userLastName;
    @Column(name = "USER_BIRTHDATE")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<View> views = new ArrayList<>();

}
