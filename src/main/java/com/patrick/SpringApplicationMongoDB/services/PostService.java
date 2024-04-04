package com.patrick.SpringApplicationMongoDB.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrick.SpringApplicationMongoDB.domain.Post;
import com.patrick.SpringApplicationMongoDB.repository.PostRepository;
import com.patrick.SpringApplicationMongoDB.services.exception.ObjectNotFoundException;

@Service
public class PostService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Post obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
		return obj;
	}
	
}
