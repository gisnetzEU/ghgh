package com.example.filmstreamer.repository;

import com.example.filmstreamer.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<Iterable<User>> findUsersByUserEmail(String userEmail);
}



