package com.example.filmstreamer.controller;

import com.example.filmstreamer.model.User;
import com.example.filmstreamer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/users")
public class UserRestController {

    @Autowired
    UserService userservice;

    //here we are creating our end-point rest API User
    //CRUD: read
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Optional<Iterable<User>> usersRetrieved = userservice.getAllUsers();

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "users");

        if (usersRetrieved.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(usersRetrieved.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: read, find one user by id
    /*@GetMapping(path = "getUser")
    public ResponseEntity<User> findUserById(@RequestParam Long userId) {
        Optional<User> userFound = userservice.findUserById(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "getUser");

        if (userFound.isPresent()) {
            headers.add("operationStatus", "found");
            return ResponseEntity.accepted().headers(headers).body(userFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: create
    @PostMapping(path = "addUser", consumes = "application/JSON")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        Optional<User> userCreated = userservice.createUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "addUser");

        if (userCreated.isPresent()) {
            headers.add("operationStatus", "created");
            return ResponseEntity.accepted().headers(headers).body(userCreated.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: delete book by id
    @DeleteMapping(path = "deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam Long userId) {
        Optional<User> userFound = userservice.deleteUserById(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteUser");

        if (userFound.isPresent()) {
            headers.add("operationStatus", "deleted");
            return ResponseEntity.accepted().headers(headers).body(userFound.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }

    //CRUD: update
    /*@PutMapping(path = "updateAuthor", consumes = "application/JSON")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        Optional<Author> authorFound = authorservice.findAuthorById(author.getAuthorId());
        Optional<Author> authorUpdate = authorFound;

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateAuthor");

        if (!authorFound.isPresent()) {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else if (author.equals(authorFound.get())) {
            headers.add("operationStatus", "no data to update");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            Boolean mustUpdate = false;
            if(!author.getFirstName().equals(authorFound.get().getFirstName()) && !author.getFirstName().equals("")) {
                authorUpdate.get().setFirstName(author.getFirstName());
                headers.add("firstName", "to be updated");
                mustUpdate = true;
            }
            if(!author.getLastName().equals(authorFound.get().getLastName()) && !author.getLastName().equals("")) {
                authorUpdate.get().setLastName(author.getLastName());
                headers.add("lastName", "to be updated");
                mustUpdate = true;
            }
            if(!author.getDob().equals(authorFound.get().getDob()) && !author.getDob().equals("")) {
                authorUpdate.get().setDob(author.getDob());
                headers.add("Date of Birth", "to be updated");
                mustUpdate = true;
            }
            if((author.getBooks() != authorFound.get().getBooks()) && (!author.getBooks().isEmpty())) {
                authorUpdate.get().setBooks(author.getBooks());
                headers.add("books", "to be updated");
                mustUpdate = true;
            }
            if (mustUpdate) {
                headers.add("operationStatus", "updated");
                return ResponseEntity.accepted().headers(headers).body(authorservice.updateAuthor(authorUpdate.get()).get());
            } else {
                headers.add("operationStatus", "no valid data to update");
                return ResponseEntity.accepted().headers(headers).body(null);
            }
        }
    }*/

}
