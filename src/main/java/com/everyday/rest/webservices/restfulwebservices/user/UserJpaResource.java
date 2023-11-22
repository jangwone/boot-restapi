package com.everyday.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.everyday.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.everyday.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RequestMapping("/jpa")
@RestController
public class UserJpaResource {
	
	private UserRepository repository;
	
	private PostRepository postRrepository;
	
	public UserJpaResource(UserRepository repository, PostRepository postRrepository) {
		this.repository = repository;
		this.postRrepository =postRrepository;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUseres() {
		return repository.findAll();
	}
	
	//HAL - Json Hypertext Application Language
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) 
			throw new UserNotFoundException("id:"+id);
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		//_links:{all-user:{href: "http:~"}}
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUseres());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) 
			throw new UserNotFoundException("id:"+id);

		return user.get().getPosts();
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> retrievePostsForUser(@PathVariable int id, @Valid @RequestBody Post  post) {
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) 
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postRrepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
