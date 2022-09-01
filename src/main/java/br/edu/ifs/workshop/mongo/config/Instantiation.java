package br.edu.ifs.workshop.mongo.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.edu.ifs.workshop.mongo.domain.Post;
import br.edu.ifs.workshop.mongo.domain.User;
import br.edu.ifs.workshop.mongo.repositories.PostRepository;
import br.edu.ifs.workshop.mongo.repositories.UserRepository;

@Configuration
@Profile("test")
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();

		User marilia = new User(null, "Marilia Grey", "mariri_grey@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		Post p1 = new Post(null, new Date(System.currentTimeMillis()), "Mas que loucura",
				"Juro que vi a maria conversando com a julia, logo a maria que tinha brigado feio com a julia", bob);
		
		Post p2 = new Post(null, new Date(System.currentTimeMillis() + 1928374659128L), "Bom dia", "Bom dia povo!", marilia);
		
		
		userRepository.saveAll(Arrays.asList(marilia, alex, bob));
		postRepository.saveAll(Arrays.asList(p1, p2));

	}

}
