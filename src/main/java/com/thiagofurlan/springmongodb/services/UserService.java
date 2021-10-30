package com.thiagofurlan.springmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagofurlan.springmongodb.domain.User;
import com.thiagofurlan.springmongodb.dto.UserDTO;
import com.thiagofurlan.springmongodb.exception.ObjectNotFoundException;
import com.thiagofurlan.springmongodb.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		User user = repository.findById(id).orElse(null);
		
		if (user == null) {
			throw new ObjectNotFoundException("User not found");
		}
		
		return user;
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public void deleteById(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
