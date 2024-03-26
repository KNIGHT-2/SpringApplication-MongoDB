package com.patrick.SpringApplicationMongoDB.resources;

import com.patrick.SpringApplicationMongoDB.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        User test = new User("1", "Test User", "test@email.com");
        User test2 = new User("2", "Test User2", "test2@email.com");

        List<User> list = new ArrayList<>();

        list.addAll(Arrays.asList(test, test2));

        return ResponseEntity.ok().body(list);
    }
}
