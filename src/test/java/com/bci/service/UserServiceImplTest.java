package com.bci.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bci.dto.PhonesDto;
import com.bci.dto.ResponseDto;
import com.bci.dto.UserDto;
import com.bci.entity.Phone;
import com.bci.entity.User;
import com.bci.exception.InvalidDataException;
import com.bci.exception.ResourceConflictException;
import com.bci.repository.PhoneRepository;
import com.bci.repository.UserRepository;
import com.bci.util.GeneralMessages;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PhoneRepository phoneRepository;

	@InjectMocks
	private UserServiceImpl userService; // Cambia aquí a la implementación concreta

	private UserDto userDto;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		userDto = new UserDto();
		userDto.setName("Juan");
		userDto.setEmail("juan@dominio.cl");
		userDto.setPassword("Password123");

		List<PhonesDto> phones = new ArrayList<>();
		PhonesDto phone = new PhonesDto();
		phone.setCitycode(2);
		phone.setContrycode(56);
		phone.setNumber("987654321");
		phones.add(phone);

		userDto.setPhones(phones);
	}

	// Esta prueba verifica que un usuario válido se guarde correctamente.
	@Test
	public void save_ShouldReturnResponseDto_WhenUserIsValid() throws Exception {
		when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);

		User savedUser = new User();
		savedUser.setId(UUID.randomUUID());
		savedUser.setEmail(userDto.getEmail());
		when(userRepository.save(any(User.class))).thenReturn(savedUser);

		ResponseDto response = userService.save(userDto);

		assertNotNull(response);
		assertEquals(GeneralMessages.EXITOSO, response.getMensaje());
		verify(userRepository, times(1)).save(any(User.class));
		verify(phoneRepository, times(1)).save(any(Phone.class)); // Verifica que se guarden los teléfonos
	}

	// Esta prueba verifica que se lance una excepción si el usuario ya existe.
	@Test
	public void save_ShouldThrowResourceConflictException_WhenUserAlreadyExists() {
		// Arrange
		when(userRepository.findByEmail(userDto.getEmail())).thenReturn(new User());

		// Act & Assert
		assertThrows(ResourceConflictException.class, () -> {
			userService.save(userDto);
		});

		// Verifica que el repositorio no haya guardado un nuevo usuario
		verify(userRepository, never()).save(any(User.class));
	}

	// Esta prueba verifica que se guarden los teléfonos del usuario.
	@Test
	public void saveUser_ShouldSavePhones_WhenPhonesAreProvided() throws Exception {
		User user = new User();
		user.setId(UUID.randomUUID());

		when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
		when(userRepository.save(any(User.class))).thenReturn(user);

		userService.save(userDto);

		verify(phoneRepository, times(1)).save(any(Phone.class)); // Verifica que se guarden los teléfonos
	}

}
