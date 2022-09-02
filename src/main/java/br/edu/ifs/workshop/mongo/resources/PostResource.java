package br.edu.ifs.workshop.mongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifs.workshop.mongo.domain.Post;
import br.edu.ifs.workshop.mongo.resources.util.URL;
import br.edu.ifs.workshop.mongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		return ResponseEntity.ok(postService.findById(id));
	}

	@GetMapping("/title-search")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
		var text = URL.decodeParam(title);
		List<Post> listaPosts = postService.findByTitle(text);
		return ResponseEntity.ok(listaPosts);
	}
	
	@GetMapping("/body-search")
	public ResponseEntity<List<Post>> findByBody(@RequestParam(value = "body", defaultValue = "") String body) {
		var text = URL.decodeParam(body);
		List<Post> listaPosts = postService.findByBody(text);
		return ResponseEntity.ok(listaPosts);
	}

}
