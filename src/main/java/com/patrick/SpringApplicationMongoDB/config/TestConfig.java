package com.patrick.SpringApplicationMongoDB.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.patrick.SpringApplicationMongoDB.DTO.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.patrick.SpringApplicationMongoDB.DTO.AuthorDTO;
import com.patrick.SpringApplicationMongoDB.domain.Post;
import com.patrick.SpringApplicationMongoDB.domain.User;
import com.patrick.SpringApplicationMongoDB.repository.PostRepository;
import com.patrick.SpringApplicationMongoDB.repository.UserRepository;

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

        Post post1 = new Post(null, fmt.parse("12/02/2024"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços.", new AuthorDTO(user1));
        Post post2 = new Post(null, fmt.parse("14/02/2024"), "Bom dia!", "Acordei feliz hoje.", new AuthorDTO(user1));

        postRepository.saveAll(Arrays.asList(post1, post2));

        user1.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(user1);

        CommentDTO comment1 = new CommentDTO("Boa viagem!", fmt.parse("12/02/2024"), new AuthorDTO(user2));
        CommentDTO comment2 = new CommentDTO("Aproveite.", fmt.parse("13/02/2024"), new AuthorDTO(user3));
        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", fmt.parse("14/02/2024"), new AuthorDTO(user2));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
