package com.JournalApp.Journal.service;

import com.JournalApp.Journal.entity.JournalEntry;
import com.JournalApp.Journal.entity.User;
import com.JournalApp.Journal.repository.JournalEntryRepository;
import com.JournalApp.Journal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j // To customize logging
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void saveNewEntry(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }catch(Exception e) {
            log.error("Error occured for {}", user.getName(), e); // Use of slf4j for logging
        }
    }

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public void saveNewAdmin(User admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(admin);
    }

    public Optional<User> findByID(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntry(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByName(userName);
    }
}
