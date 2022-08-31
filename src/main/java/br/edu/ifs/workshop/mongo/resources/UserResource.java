package br.edu.ifs.workshop.mongo.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifs.workshop.mongo.domain.User;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User marilia = new User("1", "Marilia Grey", "marilia_grey@gmail.com");
		User luiza = new User("2", "Luiza Blue", "luiza_blue@gmail.com");
		
		List<User> listaUsers = Arrays.asList(marilia, luiza);
		
		return ResponseEntity.ok(listaUsers);
		
	}

}
