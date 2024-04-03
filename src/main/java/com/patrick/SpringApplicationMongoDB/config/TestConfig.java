package com.patrick.SpringApplicationMongoDB.config;

import com.patrick.SpringApplicationMongoDB.DTO.AuthorDTO;
import com.patrick.SpringApplicationMongoDB.domain.Post;
import com.patrick.SpringApplicationMongoDB.domain.User;
import com.patrick.SpringApplicationMongoDB.repository.PostRepository;
import com.patrick.SpringApplicationMongoDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class TestConfig implements CommandLineRunner {
    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User user1 = new User(null, "Maria Brown", "maria@email.com");
        User user2 = new User(null, "Alex Green", "alex@email.com");
        User user3 = new User(null, "Bob Grey", "bob@email.com");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        Post post1 = new Post(null, fmt.parse("12/02/2024"), "Partiu viagem", "Vou viajar para São Paulo. Abraços", new AuthorDTO(user1));
        Post post2 = new Post(null, fmt.parse("14/02/2024"), "Bom dia!", "Acordei feliz hoje.", new AuthorDTO(user1));

        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
