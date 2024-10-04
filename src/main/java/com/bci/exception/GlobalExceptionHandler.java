package com.bci.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * Captura errores de validación en las solicitudes. Retorna un mapa con los
	 * campos que fallaron y sus mensajes de error, con un código de estado 400 Bad
	 * Request.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Maneja excepciones de argumentos ilegales. Retorna un mensaje de error
	 * específico en un mapa, con un código de estado 400 Bad Request.
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Captura excepciones de puntero nulo. Retorna un mensaje de error general
	 * indicando un problema inesperado, con un código de estado 500 Internal Server
	 * Error.
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Ocurrió un error inesperado: " + ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * Maneja excepciones específicas de datos inválidos. Retorna un mensaje de
	 * error detallado, con un código de estado 400 Bad Request.
	 */
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<Map<String, String>> handleInvalidDataException(InvalidDataException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/*
	 * Captura excepciones relacionadas con el procesamiento de JSON. Retorna un
	 * mensaje de error general sobre un problema inesperado, con un código de
	 * estado 500 Internal Server Error.
	 */
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<Map<String, String>> handleGeneralException(JsonProcessingException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Ocurrió un error inesperado: " + ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Maneja excepciones de tipo {@link ResourceBadRequestException}. Este método
	 * se utiliza para indicar que una solicitud realizada por el cliente no es
	 * válida o no se puede procesar debido a errores en la entrada del usuario.
	 */
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> resourceBadRequestException(ResourceBadRequestException ex, WebRequest request) {
		String mensaje = ex.getMessage();
		return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Maneja excepciones de tipo {@link UnauthorizedAccessException}.
	 * 
	 * Este método se utiliza cuando un usuario o cliente no tiene los permisos
	 * necesarios para acceder a un recurso o realizar una acción en particular.
	 */
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<?> handleUnauthorizedAccessException(UnauthorizedAccessException ex, WebRequest request) {
		String mensaje = ex.getMessage();
		return new ResponseEntity<>(mensaje, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Maneja excepciones de tipo {@link ResourceNotFoundException}.
	 * 
	 * Este método se utiliza cuando se intenta acceder a un recurso que no se
	 * encuentra en el sistema, como al buscar, eliminar o actualizar un usuario.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		String mensaje = ex.getMessage();
		return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	}

	/**
	 * Maneja excepciones de tipo {@link ResourceConflictException}.
	 * 
	 * Este método se utiliza para indicar que una operación no se puede completar
	 * debido a un conflicto con el estado actual del recurso en el servidor.
	 */
	@ExceptionHandler(ResourceConflictException.class)
	public ResponseEntity<?> handleResourceConflictException(ResourceConflictException ex, WebRequest request) {
		String mensaje = ex.getMessage();
		return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
	}

	/**
	 * Maneja cualquier tipo de excepción no controlada.
	 * 
	 * Este método captura cualquier excepción que pueda ocurrir y proporciona una
	 * respuesta genérica indicando que ocurrió un error inesperado.
	 */
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request) {
		String mensaje = "Ocurrió un error inesperado.";
		return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}