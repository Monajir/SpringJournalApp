package com.JournalApp.Journal.controller;


import com.JournalApp.Journal.entity.User;
import com.JournalApp.Journal.service.UserService;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> allUsers = userService.getAll();
        if(allUsers != null && !allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User admin) {
        userService.saveNewAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
