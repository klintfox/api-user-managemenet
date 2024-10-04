package com.bci.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bci.config.TestSecurityConfig;
import com.bci.dto.PhonesDto;
import com.bci.dto.ResponseDto;
import com.bci.dto.UserDto;
import com.bci.exception.ResourceBadRequestException;
import com.bci.exception.ResourceConflictException;
import com.bci.exception.ResourceNotFoundException;
import com.bci.exception.UnauthorizedAccessException;
import com.bci.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class UserControllerTests {

	private UserDto userDto;
	private List<PhonesDto> phones;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		userDto = createUserDto();
	}

	private UserDto createUserDto() {
		UserDto userDto = new UserDto();
		userDto.setName("jorgw");
		userDto.setEmail("ju33an@rodriguez.cl");
		userDto.setPassword("Urbano22");
		userDto.setPhones(createPhonesDtoList());
		return userDto;
	}

	private List<PhonesDto> createPhonesDtoList() {
		phones = new ArrayList<>();
		PhonesDto phoneOne = new PhonesDto();
		phoneOne.setNumber("987654213");
		phoneOne.setCitycode(0);
		phoneOne.setContrycode(57);
		phones.add(phoneOne);
		return phones;
	}

	@Test
	void register_UserSuccessfullyCreated_Returns201() throws Exception {
		
		ResponseDto responseDto = new ResponseDto("User created successfully");

		given(userService.save(any(UserDto.class))).willReturn(responseDto);

		// Act & Assert
		mockMvc.perform(post("/api/users/register").with(user("admin").password("admin").roles("USER"))
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isCreated())
				.andExpect(content().json("{\"mensaje\":\"User created successfully\"}"));
	}

	@Test
	void register_UserBadRequest_Returns400() throws Exception {

		// Suponiendo que el método save lanza ResourceBadRequestException
		given(userService.save(any(UserDto.class))).willThrow(new ResourceBadRequestException("Invalid data"));

		// Act & Assert
		mockMvc.perform(post("/api/users/register").with(user("admin").password("admin").roles("USER"))
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isBadRequest()).andExpect(content().string("Invalid data"));
	}

	@Test
	void register_UnauthorizedAccess_Returns401() throws Exception {

		// Suponiendo que el método save lanza UnauthorizedAccessException
		given(userService.save(any(UserDto.class))).willThrow(new UnauthorizedAccessException("Unauthorized"));

		// Act & Assert
		mockMvc.perform(post("/api/users/register").with(user("admin").password("admin2").roles("USER"))
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isUnauthorized()).andExpect(content().string("Unauthorized"));
	}

	@Test
	void register_ResourceConflict_Returns409() throws Exception {
		// Suponiendo que el método save lanza ResourceConflictException
		given(userService.save(any(UserDto.class))).willThrow(new ResourceConflictException("User already exists"));

		// Act & Assert
		mockMvc.perform(post("/api/users/register").with(user("admin").password("admin").roles("USER"))
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isConflict()).andExpect(content().string("User already exists"));
	}

	@Test
	void register_ResourceNotFound_Returns404() throws Exception {
		// Suponiendo que el método save lanza ResourceNotFoundException
		given(userService.save(any(UserDto.class))).willThrow(new ResourceNotFoundException("User not found"));

		// Act & Assert
		mockMvc.perform(post("/api/users/register").with(user("admin").password("admin").roles("USER"))
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isNotFound()).andExpect(content().string("User not found"));
	}
}
