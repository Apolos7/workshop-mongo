package br.edu.ifs.workshop.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifs.workshop.mongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
