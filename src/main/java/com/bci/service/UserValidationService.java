package com.bci.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationService {
	public static boolean validateEmailPattern(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.cl$"; // Incluir el "." antes de "cl"
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Valida si la contraseña cumple con el patrón establecido.
	 * 
	 * El patrón requiere que la contraseña contenga:
	 * <ul>
	 * <li>Al menos una letra mayúscula.</li>
	 * <li>Al menos cinco letras minúscula.</li>
	 * <li>Al menos dos dígitos.</li>
	 * <li>Una longitud mínima de 8 caracteres.</li>
	 * </ul>
	 *
	 * @param password La contraseña a validar.
	 * @return {@code true} si la contraseña cumple con el patrón; de lo contrario,
	 *         {@code false}.
	 */
	public static boolean validatePasswordPattern(String password) {
		String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
