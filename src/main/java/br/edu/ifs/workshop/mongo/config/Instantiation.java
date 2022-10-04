package br.edu.ifs.workshop.mongo.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.edu.ifs.workshop.mongo.domain.Post;
import br.edu.ifs.workshop.mongo.domain.User;
import br.edu.ifs.workshop.mongo.dto.AuthorDTO;
import br.edu.ifs.workshop.mongo.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(marilia, alex, bob));

		Post p1 = new Post(null, new Date(System.currentTimeMillis()), "Mas que loucura",
				"Juro que vi a maria conversando com a julia, logo a maria que tinha brigado feio com a julia", new AuthorDTO(bob));
		Post p2 = new Post(null, new Date(System.currentTimeMillis() + 9128L), "Bom dia", "Bom dia povo!", new AuthorDTO(marilia));
		
		
		CommentDTO c1 = new CommentDTO("Acho que você não devia fofocar tanto mestre.", new Date(System.currentTimeMillis() + 98127364L), new AuthorDTO(alex));
		
		CommentDTO c2 = new CommentDTO("Bom dia pra você também moça.", new Date(System.currentTimeMillis()+ 9998374659128L), new AuthorDTO(alex));
		
		p1.getComments().add(c1);
		p2.getComments().add(c2);
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		marilia.getListPost().add(p2);
		bob.getListPost().add(p1);
		userRepository.saveAll(Arrays.asList(marilia, bob));

	}

}
