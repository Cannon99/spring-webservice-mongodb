package com.thiagofurlan.springmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagofurlan.springmongodb.domain.Post;
import com.thiagofurlan.springmongodb.exception.ObjectNotFoundException;
import com.thiagofurlan.springmongodb.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Post post = repository.findById(id).orElse(null);
		
		if (post == null) {
			throw new ObjectNotFoundException("Post not found");
		}	
		return post;
	}
	
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> searchTitle(String text) {
		return repository.searchTitle(text);
	}
}
