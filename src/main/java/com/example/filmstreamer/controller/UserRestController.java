package com.example.filmstreamer.controller;

import com.example.filmstreamer.model.User;
import com.example.filmstreamer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


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
    @GetMapping(path = "/getUser")
    public ResponseEntity<User> findUserById(@RequestParam UUID userUUID) {
        Optional<User> userFound = userservice.findUserById(userUUID);

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
    @PostMapping(path = "/addUser", consumes = "application/JSON")
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
    @DeleteMapping(path = "/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam UUID userId) {
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
    @PutMapping(path = "/updateUser", consumes = "application/JSON")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> userFound = userservice.findUserById(user.getUserUUID());
        Optional<User> userUpdate = userFound;

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateUser");

        if (!userFound.isPresent()) {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else if (user.equals(userFound.get())) {
            headers.add("operationStatus", "no data to update");
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            Boolean mustUpdate = false;
            if(!user.getUserFirstName().equals(userFound.get().getUserFirstName()) && !user.getUserFirstName().equals("")) {
                userUpdate.get().setUserFirstName(user.getUserFirstName());
                headers.add("user firstname", "to be updated");
                mustUpdate = true;
            }
            if(!user.getUserLastName().equals(userFound.get().getUserLastName()) && !user.getUserLastName().equals("")) {
                userUpdate.get().setUserLastName(user.getUserLastName());
                headers.add("user last name", "to be updated");
                mustUpdate = true;
            }
            if(!user.getBirthDate().equals(userFound.get().getBirthDate()) && !user.getBirthDate().equals("")) {
                userUpdate.get().setBirthDate(user.getBirthDate());
                headers.add("birthdate", "to be updated");
                mustUpdate = true;
            }
            if(!user.getUserRol().equals(userFound.get().getUserRol()) && !user.getUserRol().equals("")) {
                userUpdate.get().setUserRol(user.getUserRol());
                headers.add("user rol", "to be updated");
                mustUpdate = true;
            }
            if(!user.getUserEmail().equals(userFound.get().getUserEmail()) && !user.getUserEmail().equals("")) {
                userUpdate.get().setUserEmail(user.getUserEmail());
                headers.add("user email", "to be updated");
                mustUpdate = true;
            }
            if((user.getPassword() != userFound.get().getPassword()) && (user.getPassword() != 0)) {
                userUpdate.get().setPassword(user.getPassword());
                headers.add("password", "to be updated");
                mustUpdate = true;
            }
            if (mustUpdate) {
                headers.add("operationStatus", "updated");
                return ResponseEntity.accepted().headers(headers).body(userservice.updateUser(userUpdate.get()).get());
            } else {
                headers.add("operationStatus", "no valid data to update");
                return ResponseEntity.accepted().headers(headers).body(null);
            }
        }
    }

}
