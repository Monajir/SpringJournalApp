package com.JournalApp.Journal.ServiceTest;

import com.JournalApp.Journal.service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Disabled
    @Test
    void testSendMail(){
        emailService.sendEmail("monajirwantswar@gmail.com",
                "Testing Java Mail sender",
                "Hi, there. Testing 1, 2, 3...");
    }
}
