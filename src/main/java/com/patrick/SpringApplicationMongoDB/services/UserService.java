package com.patrick.SpringApplicationMongoDB.services;

import com.patrick.SpringApplicationMongoDB.DTO.UserDTO;
import com.patrick.SpringApplicationMongoDB.domain.User;
import com.patrick.SpringApplicationMongoDB.repository.UserRepository;
import com.patrick.SpringApplicationMongoDB.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

    public User insert(User obj){
        return repository.insert(obj);
    }
    public User fromDTO(UserDTO dto){
        User obj = new User(dto.getId(),dto.getName(),dto.getEmail());
        return obj;
    }
    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }

    public User update(User newObj){
        User obj = repository.findById(newObj.getId()).orElseThrow( () -> new ObjectNotFoundException("Object not found"));

        if(newObj.getEmail() != null){
            obj.setEmail(newObj.getEmail());
        }
        if(newObj.getName() != null){
            obj.setName(newObj.getName());
        }
        return repository.save(obj);
    }
}
