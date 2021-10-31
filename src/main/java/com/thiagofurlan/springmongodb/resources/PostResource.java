package com.thiagofurlan.springmongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thiagofurlan.springmongodb.domain.Post;
import com.thiagofurlan.springmongodb.services.PostService;
import com.thiagofurlan.springmongodb.util.QueryParamsDecoder;

@RestController
@RequestMapping("/posts")
public class PostResource {
	@Autowired
	private PostService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(path = "/title-search")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = QueryParamsDecoder.decode(text);
		List<Post> posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(path = "/title-search-2")
	public ResponseEntity<List<Post>> findByTitle2(@RequestParam(value = "text", defaultValue = "") String text) {
		text = QueryParamsDecoder.decode(text);
		List<Post> posts = service.searchTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(path = "/full-search")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "min-date", defaultValue = "") String minDate,
			@RequestParam(value = "max-date", defaultValue = "") String maxDate) {
		text = QueryParamsDecoder.decode(text);
		Date min = QueryParamsDecoder.convertDate(minDate, new Date(0L));
		Date max = QueryParamsDecoder.convertDate(maxDate, new Date());
		List<Post> posts = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(posts);
	}
}
