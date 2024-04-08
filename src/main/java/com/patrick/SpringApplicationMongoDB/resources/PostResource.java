package com.patrick.SpringApplicationMongoDB.resources;

import com.patrick.SpringApplicationMongoDB.resources.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patrick.SpringApplicationMongoDB.domain.Post;
import com.patrick.SpringApplicationMongoDB.services.PostService;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		return ResponseEntity.ok().body(service.findByTitle(text));
	}
}
