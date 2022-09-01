package br.edu.ifs.workshop.mongo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifs.workshop.mongo.domain.Post;
import br.edu.ifs.workshop.mongo.dto.PostDTO;
import br.edu.ifs.workshop.mongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping
	public ResponseEntity<List<PostDTO>> findAll() {
		List<PostDTO> list = postService.findAll().stream().map(post -> new PostDTO(post)).toList();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		return ResponseEntity.ok(new PostDTO(postService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PostDTO postDTO) {
		Post post = postService.fromDTO(postDTO);
		post = postService.insert(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Post post) {
		postService.update(post);
		return ResponseEntity.noContent().build();

	}

}
