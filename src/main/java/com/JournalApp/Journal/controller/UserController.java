package com.JournalApp.Journal.controller;

import com.JournalApp.Journal.apiResponse.WeatherResponse;
import com.JournalApp.Journal.entity.User;
import com.JournalApp.Journal.repository.UserRepository;
import com.JournalApp.Journal.service.UserService;
import com.JournalApp.Journal.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userIdDb = userService.findByUserName(userName);

        userIdDb.setName(user.getName());
        userIdDb.setPassword(user.getPassword());
        userService.saveNewEntry(userIdDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         WeatherResponse response = weatherService.getWeather("Dhaka");
         String greeting = "";
         if(response != null) {
             greeting = " Weather feels like " + response.getCurrent().getTemperature();
         }
        return new ResponseEntity<>("Hi, " + authentication.getName() + greeting, HttpStatus.OK);
    }
}
