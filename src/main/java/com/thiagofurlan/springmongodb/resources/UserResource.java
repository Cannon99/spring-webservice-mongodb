package com.thiagofurlan.springmongodb.resources;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
		User user = service.fromDTO(userDTO);
		user = service.insert(user);
		
		// Get current location from the new object inserted on database
		// URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		// return ResponseEntity.created(uri).build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
		User user = service.fromDTO(userDTO);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
