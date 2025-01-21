package com.JournalApp.Journal.Scheduler;

import com.JournalApp.Journal.entity.JournalEntry;
import com.JournalApp.Journal.entity.User;
import com.JournalApp.Journal.enums.Sentiment;
import com.JournalApp.Journal.repository.UserRepositoryImpl;
import com.JournalApp.Journal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 0 9 * * SUN") // Scheduled to auto-send every Sunday at 9 am in morning.
    public void fetchUserAndSendSAMail(){
        List<User> users = userRepositoryImpl.getUserForSA();

        for(User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for(Sentiment sentiment : sentiments) {
                if(sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if(entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiment.toString());
            }
        }
    }
}
