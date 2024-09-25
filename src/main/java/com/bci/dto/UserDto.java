package com.bci.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -5069229940876613178L;

	@Schema(description = "Name: ", example = "Juan")
	private String name;

	@Schema(description = "Email: ", example = "aaaaaa@dominio.cl")
	private String email;

	@Schema(description = "Password pattern: ", example = "Abcdef12")
	private String password;
		
	private List<PhonesDto> phones;

	public UserDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhonesDto> getPhones() {
		return phones;
	}

	public void setPhones(List<PhonesDto> phones) {
		this.phones = phones;
	}

	public UserDto(String name, String email, String password, List<PhonesDto> phones) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
	}

}
