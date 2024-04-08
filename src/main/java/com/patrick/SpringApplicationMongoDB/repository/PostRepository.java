package com.patrick.SpringApplicationMongoDB.repository;

import com.patrick.SpringApplicationMongoDB.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }") //Fazendo uma consulta pelo "title" ignorando o Case.
    List<Post> searchTitle(String text);                 //(Este método faz a mesma coisa que o de cima.

    @Query("{ $and: [ { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }," +
            " { 'comments.text': { $regex: ?0, $options: 'i' } } ] }, { 'date': { $gte: ?1, $lte: ?2 } } ] }")
    List<Post> fullSearch(String text, Date startDate, Date endDate);
}

