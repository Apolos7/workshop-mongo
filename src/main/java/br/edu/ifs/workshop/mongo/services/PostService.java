package br.edu.ifs.workshop.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifs.workshop.mongo.domain.Post;
import br.edu.ifs.workshop.mongo.repositories.PostRepository;
import br.edu.ifs.workshop.mongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Post insert(Post post) {
		return postRepository.insert(post);
	}
	
	public void delete(String id) {
		postRepository.delete(findById(id));
	}
	
	public Post update(Post post) {
		Post newPost = findById(post.getId());
		BeanUtils.copyProperties(post, newPost);
		return postRepository.save(newPost);
	}
	
}
