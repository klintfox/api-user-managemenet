package com.bci.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ErrorResponseDtoTest {

	@Test
	public void testConstructor() {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto("KEY", "Este es un mensaje de error");
		assertEquals("Este es un mensaje de error", errorResponseDto.getMensaje());
	}

	@Test
	public void testGettersAndSetters() {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto("KEY", "Mensaje inicial");

		// Verificar el getter
		assertEquals("Mensaje inicial", errorResponseDto.getMensaje());

		// Usar el setter para cambiar el mensaje
		errorResponseDto.setMensaje("Mensaje actualizado");
		assertEquals("Mensaje actualizado", errorResponseDto.getMensaje());
	}

	@Test
	public void testSetMensaje_NullValue() {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto("KEY", "Mensaje inicial");

		// Asignar un valor nulo
		errorResponseDto.setMensaje(null);
		assertNull(errorResponseDto.getMensaje());
	}
}