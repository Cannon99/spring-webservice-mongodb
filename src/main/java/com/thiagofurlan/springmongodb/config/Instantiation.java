package com.thiagofurlan.springmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.thiagofurlan.springmongodb.domain.User;
import com.thiagofurlan.springmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User user = new User(null, "Azir", "azir@azir");
		User user2 = new User(null, "Ornn", "ornn@ornn");
		User user3 = new User(null, "Mordekaiser", "mordekaiser@mordekaiser");
	
		userRepository.saveAll(Arrays.asList(user, user2, user3));
	}
}
