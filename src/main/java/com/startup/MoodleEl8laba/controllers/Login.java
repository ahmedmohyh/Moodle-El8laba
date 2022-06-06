package com.startup.MoodleEl8laba.controllers;

import com.startup.MoodleEl8laba.models.User;
import com.startup.MoodleEl8laba.repository.UserRepository;
import com.startup.MoodleEl8laba.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class Login {

    private final UserService userService;
    @Autowired
    public Login(UserService service) {
        this.userService = service;
    }



    @PostMapping("")
    public ResponseEntity<?> tryLogin (@RequestBody User user ){

        try {
            User loginUser =  this.userService.tryLogin(user);
            return new ResponseEntity<>(loginUser,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }


    }
}
