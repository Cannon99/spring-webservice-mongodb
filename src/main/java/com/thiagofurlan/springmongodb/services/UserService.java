package com.thiagofurlan.springmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagofurlan.springmongodb.domain.User;
import com.thiagofurlan.springmongodb.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
}
