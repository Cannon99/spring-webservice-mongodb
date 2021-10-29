package com.thiagofurlan.springmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagofurlan.springmongodb.domain.User;
import com.thiagofurlan.springmongodb.exception.ObjectNotFoundException;
import com.thiagofurlan.springmongodb.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findByid(String id) {
		User user = repository.findById(id).orElse(null);
		
		if (user == null) {
			System.out.println("USER NULL");
			throw new ObjectNotFoundException("User not found");
		}
		
		return user;
	}
}
