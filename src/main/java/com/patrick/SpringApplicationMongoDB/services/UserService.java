package com.patrick.SpringApplicationMongoDB.services;

import com.patrick.SpringApplicationMongoDB.domain.User;
import com.patrick.SpringApplicationMongoDB.repository.UserRepository;
import com.patrick.SpringApplicationMongoDB.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        User obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        return obj;
    }
}
