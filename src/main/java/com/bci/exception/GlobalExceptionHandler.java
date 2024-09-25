package com.bci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Maneja excepciones de tipo {@link ResourceBadRequestException}.
	 * 
	 * Este método se utiliza para indicar que una solicitud realizada por el cliente 
	 * no es válida o no se puede procesar debido a errores en la entrada del usuario.
	 * 
	 * @param ex La excepción que se ha lanzado.
	 * @param request La solicitud del cliente que causó la excepción.
	 * @return Una respuesta con un mensaje de error y un estado HTTP 400 (Bad Request).
	 */
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> resourceBadRequestException(ResourceBadRequestException ex, WebRequest request) {
	    String mensaje = ex.getMessage();
	    return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Maneja excepciones de tipo {@link UnauthorizedAccessException}.
	 * 
	 * Este método se utiliza cuando un usuario o cliente no tiene los permisos necesarios 
	 * para acceder a un recurso o realizar una acción en particular.
	 * 
	 * @param ex La excepción que se ha lanzado.
	 * @param request La solicitud del cliente que causó la excepción.
	 * @return Una respuesta con un mensaje de error y un estado HTTP 401 (Unauthorized).
	 */
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<?> handleUnauthorizedAccessException(UnauthorizedAccessException ex, WebRequest request) {
	    String mensaje = ex.getMessage();
	    return new ResponseEntity<>(mensaje, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Maneja excepciones de tipo {@link ResourceNotFoundException}.
	 * 
	 * Este método se utiliza cuando se intenta acceder a un recurso que no se encuentra 
	 * en el sistema, como al buscar, eliminar o actualizar un usuario.
	 * 
	 * @param ex La excepción que se ha lanzado.
	 * @param request La solicitud del cliente que causó la excepción.
	 * @return Una respuesta con un mensaje de error y un estado HTTP 404 (Not Found).
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    String mensaje = ex.getMessage();
	    return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	}

	/**
	 * Maneja excepciones de tipo {@link ResourceConflictException}.
	 * 
	 * Este método se utiliza para indicar que una operación no se puede completar debido 
	 * a un conflicto con el estado actual del recurso en el servidor.
	 * 
	 * @param ex La excepción que se ha lanzado.
	 * @param request La solicitud del cliente que causó la excepción.
	 * @return Una respuesta con un mensaje de error y un estado HTTP 409 (Conflict).
	 */
	@ExceptionHandler(ResourceConflictException.class)
	public ResponseEntity<?> handleResourceConflictException(ResourceConflictException ex, WebRequest request) {
	    String mensaje = ex.getMessage();
	    return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
	}

	/**
	 * Maneja cualquier tipo de excepción no controlada.
	 * 
	 * Este método captura cualquier excepción que pueda ocurrir y proporciona una respuesta
	 * genérica indicando que ocurrió un error inesperado.
	 * 
	 * @param ex La excepción que se ha lanzado.
	 * @param request La solicitud del cliente que causó la excepción.
	 * @return Una respuesta con un mensaje de error y un estado HTTP 500 (Internal Server Error).
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request) {
	    String mensaje = "Ocurrió un error inesperado.";
	    return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}