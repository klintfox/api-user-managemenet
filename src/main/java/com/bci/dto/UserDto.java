package com.bci.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -5069229940876613178L;

	@Schema(description = "Name: ", example = "Juan")
    @NotBlank(message = "{name.not.null}")
    private String name;

    @Schema(description = "Email: ", example = "aaaaaa@dominio.cl")
    @NotBlank(message = "{email.not.null}")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.cl$", message = "{email.invalid}")
    private String email;

    @Schema(description = "Password pattern: ", example = "Abcdef12")
    @NotBlank(message = "{password.not.null}")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).{8,}$", message = "{password.invalid}")
    private String password;

    @NotEmpty(message = "{phones.not.empty}")
    @Size(min = 1, message = "{phones.size.min}")
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
