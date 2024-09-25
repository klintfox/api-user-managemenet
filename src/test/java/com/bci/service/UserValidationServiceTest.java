package com.bci.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserValidationServiceTest {

    @Test
    public void validateEmailPattern_ShouldReturnTrue_ForValidEmail() {
        String email = "example@domain.cl";
        assertTrue(UserValidationService.validateEmailPattern(email));
    }

    @Test
    public void validateEmailPattern_ShouldReturnFalse_ForInvalidEmail() {
        String email = "example@domain.com"; // Incorrecto por no terminar en ".cl"
        assertFalse(UserValidationService.validateEmailPattern(email));
    }

    @Test
    public void validateEmailPattern_ShouldReturnFalse_ForEmptyEmail() {
        String email = "";
        assertFalse(UserValidationService.validateEmailPattern(email));
    }

    @Test
    public void validatePasswordPattern_ShouldReturnTrue_ForValidPassword() {
        String password = "Aabcty12"; // Una letra mayúscula, letras minúsculas, 5 caracteres más y 2 dígitos
        assertTrue(UserValidationService.validatePasswordPattern(password));
    }

    @Test
    public void validatePasswordPattern_ShouldReturnFalse_ForInvalidPassword() {
        String password = "abc12"; // No contiene letra mayúscula
        assertFalse(UserValidationService.validatePasswordPattern(password));
    }

    @Test
    public void validatePasswordPattern_ShouldReturnFalse_ForEmptyPassword() {
        String password = "";
        assertFalse(UserValidationService.validatePasswordPattern(password));
    }

    @Test
    public void validatePasswordPattern_ShouldReturnFalse_ForShortPassword() {
        String password = "A1"; // Muy corto
        assertFalse(UserValidationService.validatePasswordPattern(password));
    }
}