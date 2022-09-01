package br.edu.ifs.workshop.mongo.dto;

import org.springframework.beans.BeanUtils;

import br.edu.ifs.workshop.mongo.domain.User;

public class AuthorDTO {

	private String id;
	private String name;

	public AuthorDTO() {

	}

	public AuthorDTO(User user) {
		BeanUtils.copyProperties(user, this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
