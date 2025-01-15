package com.JournalApp.Journal.service;

import com.JournalApp.Journal.entity.JournalEntry;
import com.JournalApp.Journal.entity.User;
import com.JournalApp.Journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry newEntry = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(newEntry);
            // Saving the updated user to the database
            userService.saveEntry(user);
        }catch(Exception e) {
            throw new RuntimeException("An error has occured while saving journal or user", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> findByID(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteEntry(ObjectId id, String userName) {
        boolean removed =  false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("An error occured while deleting the entry", e);
        }
        return removed;
    }
}
