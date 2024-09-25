package com.bci.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.bci.config.TestSecurityConfig;
import com.bci.dto.PhonesDto;
import com.bci.dto.ResponseDto;
import com.bci.dto.UserDto;
import com.bci.entity.Phone;
import com.bci.entity.User;
import com.bci.exception.ResourceBadRequestException;
import com.bci.exception.ResourceConflictException;
import com.bci.repository.PhoneRepository;
import com.bci.repository.UserRepository;
import com.bci.util.GeneralMessages;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PhoneRepository phoneRepository;

	private UserDto userDto;

	// Prepara un objeto UserDto antes de cada prueba para asegurar que todas las
	// pruebas empiecen con un estado conocido.
	@BeforeEach
	public void setUp() {
		userDto = new UserDto();
		userDto.setName("Test User");
		userDto.setEmail("test@example.cl");
		userDto.setPassword("Hunter25");
		userDto.setPhones(Collections.emptyList());
	}

	// Verifica que el método save retorne un ResponseDto válido cuando el usuario
	// es válido.
	@Test
	public void save_ShouldReturnResponseDto_WhenUserIsValid() throws Exception {
		when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);		
		
		User savedUser = new User();
	    savedUser.setId(new UUID(0, 0)); // Simular un ID
	    savedUser.setEmail(userDto.getEmail());
	    when(userRepository.save(any(User.class))).thenReturn(savedUser); // Simular el guardado

	    ResponseDto response = userService.save(userDto);		

		assertNotNull(response);
		verify(userRepository, times(1)).save(any(User.class));
		assertEquals(GeneralMessages.EXITOSO, response.getMensaje());
	}

	// Comprueba que se lanza una ResourceBadRequestException cuando el formato del
	// correo electrónico es inválido.
	@Test
	public void save_ShouldThrowException_WhenEmailIsInvalid() {
		userDto.setEmail("invalid-email");

		Exception exception = assertThrows(ResourceBadRequestException.class, () -> {
			userService.save(userDto);
		});

		assertEquals(GeneralMessages.ERROR_EMAIL_FORMAT, exception.getMessage());
	}

	// Asegura que se lanza una excepción cuando la contraseña no cumple con los
	// requisitos.
	@Test
	public void save_ShouldThrowException_WhenPasswordIsInvalid() {
		userDto.setPassword("invalid"); // No cumple con los requisitos

		Exception exception = assertThrows(ResourceBadRequestException.class, () -> {
			userService.save(userDto);
		});

		assertEquals(GeneralMessages.ERROR_PASSWORD_FORMAT + GeneralMessages.PASSWORD_FORMAT, exception.getMessage());
	}

	// Verifica que se lanza una excepción cuando ya existe un usuario con el mismo
	// correo electrónico.
	@Test
	public void save_ShouldThrowException_WhenUserExists() {
		User existingUser = new User();
		existingUser.setEmail(userDto.getEmail());

		when(userRepository.findByEmail(userDto.getEmail())).thenReturn(existingUser);

		Exception exception = assertThrows(ResourceConflictException.class, () -> {
			userService.save(userDto);
		});

		assertEquals(GeneralMessages.ERROR_USER_EXISTS, exception.getMessage());
	}

	// Comprueba que se guarden los teléfonos en la base de datos cuando se
	// proporcionan.
	@Test
	public void save_ShouldSavePhones_WhenPhonesAreProvided() throws Exception {
		PhonesDto phoneDto = new PhonesDto();
		phoneDto.setCitycode(1);
		phoneDto.setCountrycode(56);
		userDto.setPhones(Collections.singletonList(phoneDto));

		when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
		
		User savedUser = new User();
	    savedUser.setId(new UUID(0, 0)); // Simular un ID
	    savedUser.setEmail(userDto.getEmail());
	    when(userRepository.save(any(User.class))).thenReturn(savedUser); // Simular el guardado

		ResponseDto response = userService.save(userDto);

		assertNotNull(response);
		verify(phoneRepository, times(1)).save(any(Phone.class));
		assertEquals(GeneralMessages.EXITOSO, response.getMensaje());
	}
}
