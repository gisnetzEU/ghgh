package com.example.filmstreamer.repository;

import com.example.filmstreamer.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<Iterable<User>> findUsersByUserEmail(String userEmail);
}



