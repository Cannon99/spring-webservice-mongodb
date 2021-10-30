package com.thiagofurlan.springmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thiagofurlan.springmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
