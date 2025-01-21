package com.JournalApp.Journal.ServiceTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void test() {
        redisTemplate.opsForValue().set("email", "mona@gmail.com");
//        Object name = redisTemplate.opsForValue().get("name");
         int a = 1;
    }
}
