package com.thiagofurlan.springmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.thiagofurlan.springmongodb.domain.Post;
import com.thiagofurlan.springmongodb.domain.User;
import com.thiagofurlan.springmongodb.dto.AuthorDTO;
import com.thiagofurlan.springmongodb.repository.PostRepository;
import com.thiagofurlan.springmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User user1 = new User(null, "Azir", "azir@azir");
		User user2 = new User(null, "Ornn", "ornn@ornn");
		User user3 = new User(null, "Mordekaiser", "mordekaiser@mordekaiser");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
	
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Post 1", "Post body 1", new AuthorDTO(user1));
		Post post2 = new Post(null, sdf.parse("23/03/2019"), "Post 2", "Post body 2", new AuthorDTO(user2));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		user1.getPosts().add(post1);
		user2.getPosts().add(post2);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
	}
}
