package br.edu.ifs.workshop.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifs.workshop.mongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query(value = "{ 'body': { $regex: '?0', $options: 'i' } }", fields = "body")
	List<Post> searchBody(String body);

	List<Post> findByTitleContainingIgnoreCase(String title);
}
