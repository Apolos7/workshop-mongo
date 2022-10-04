package br.edu.ifs.workshop.mongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifs.workshop.mongo.domain.Post;
import br.edu.ifs.workshop.mongo.repositories.PostRepository;
import br.edu.ifs.workshop.mongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> findByTitle(String title) {
		return postRepository.findByTitleContainingIgnoreCase(title);
	}

	public List<Post> findByBody(String text) {
		return postRepository.searchBody(text);
	}
	
	public List<Post> complexSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.complexSearch(text, minDate, maxDate);
	}

}
