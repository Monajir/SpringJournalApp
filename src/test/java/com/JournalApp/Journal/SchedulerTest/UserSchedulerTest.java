package com.JournalApp.Journal.SchedulerTest;

import com.JournalApp.Journal.Scheduler.UserScheduler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    UserScheduler userScheduler;

    @Disabled
    @Test
    public void testScheduler() {
        userScheduler.fetchUserAndSendSAMail();
    }
}
