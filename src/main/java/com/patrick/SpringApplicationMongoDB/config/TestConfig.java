package com.patrick.SpringApplicationMongoDB.config;

import com.patrick.SpringApplicationMongoDB.domain.User;
import com.patrick.SpringApplicationMongoDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        User user1 = new User(null, "Maria Brown", "maria@email.com");
        User user2 = new User(null, "Alex Green", "alex@email.com");
        User user3 = new User(null, "Bob Grey", "bob@email.com");

        repository.saveAll(Arrays.asList(user1, user2, user3));
    }
}
