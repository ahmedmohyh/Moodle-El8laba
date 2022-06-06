package com.startup.MoodleEl8laba.controllers;

import com.startup.MoodleEl8laba.models.User;
import com.startup.MoodleEl8laba.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class Register {

    private final UserService userService;
    @Autowired
    public Register(UserService service) {
        this.userService = service;
    }

    @GetMapping("/userNameExists/{userName}")
    public boolean userNameExists (@PathVariable String userName) {
        //System.out.println("from the userNameExist the userName is " + userName);
        return  this.userService.userNameExists(userName);
    }

    @PostMapping("")
    public ResponseEntity<?> tryRegister (@RequestBody User user ){
        System.out.println(user.toString());
        try {
          User newUser =   this.userService.tryRegister(user);
          return  new ResponseEntity<>(newUser,HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
