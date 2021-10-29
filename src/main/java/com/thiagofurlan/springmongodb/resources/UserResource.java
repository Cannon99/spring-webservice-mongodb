package com.thiagofurlan.springmongodb.resources;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagofurlan.springmongodb.domain.User;
import com.thiagofurlan.springmongodb.dto.UserDTO;
import com.thiagofurlan.springmongodb.services.UserService;

@RestController
@RequestMapping(value="users")
public class UserResource {
	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		Function<User, UserDTO> func = (User user) -> {
			return new UserDTO(user);
		};
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(func).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findByid(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
}
