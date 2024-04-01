package com.patrick.SpringApplicationMongoDB.resources;

import com.patrick.SpringApplicationMongoDB.DTO.UserDTO;
import com.patrick.SpringApplicationMongoDB.domain.User;
import com.patrick.SpringApplicationMongoDB.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(currentUser -> new UserDTO(currentUser)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO objDto){
        User obj = service.insert(service.fromDTO(objDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
       // return ResponseEntity.ok().body(obj);
        return ResponseEntity.created(uri).build();
    }
}
