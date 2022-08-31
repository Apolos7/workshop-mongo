package br.edu.ifs.workshop.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifs.workshop.mongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
