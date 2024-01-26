package org.example.users.controller;


import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.users.model.User;
import org.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/eTribute")
public class UserController {

    Logger LOGGER = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getUserById/{user_id}")
    public User getUserById(@PathVariable(name = "user_id") int user_id) {
        return userService.getUserById(user_id);
    }

    @GetMapping("getAllUsers/")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @Transactional
    @PostMapping(value = "/createUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/login/{token}")
    public User getUserLogin(@PathVariable(name = "token") String token) {
        return userService.getUserLogin(token);
    }


}
