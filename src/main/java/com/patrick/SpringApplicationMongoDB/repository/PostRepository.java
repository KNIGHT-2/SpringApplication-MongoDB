package com.patrick.SpringApplicationMongoDB.repository;

import com.patrick.SpringApplicationMongoDB.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
