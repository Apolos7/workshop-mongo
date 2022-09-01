package br.edu.ifs.workshop.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifs.workshop.mongo.domain.User;
import br.edu.ifs.workshop.mongo.dto.UserDTO;
import br.edu.ifs.workshop.mongo.repositories.UserRepository;
import br.edu.ifs.workshop.mongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		userRepository.delete(findById(id));
	}
	
	public User update(User user) {
		User newUser = findById(user.getId());
		BeanUtils.copyProperties(user, newUser);
		return userRepository.save(newUser);
	}
	
	public User fromDTO(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}
	
}
