package com.patrick.SpringApplicationMongoDB.resources;

import com.patrick.SpringApplicationMongoDB.DTO.UserDTO;
import com.patrick.SpringApplicationMongoDB.domain.Post;
import com.patrick.SpringApplicationMongoDB.domain.User;
import com.patrick.SpringApplicationMongoDB.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDTO newObjDto){
        User obj = service.fromDTO(newObjDto);
        obj.setId(id);
        service.update(obj);
        //return ResponseEntity.ok().body(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(value = "{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
    	User obj = service.findById(id);
    	return ResponseEntity.ok().body(obj.getPosts());
    }
}
