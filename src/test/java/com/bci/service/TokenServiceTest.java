package com.bci.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenServiceTest {

	@Test
	public void generateToken_ShouldReturnValidToken_ForGivenUsername() {
		String username = "testUser";

		// Genera el token para el usuario especificado.
		String token = TokenService.generateToken(username);

		// Verifica que el token generado no sea nulo o vacío.
		assertNotNull(token);
		assertFalse(token.isEmpty());

		// Extrae las reclamaciones (claims) del token.
		Claims claims = Jwts.parser().setSigningKey("mySecretKey".getBytes()).parseClaimsJws(token).getBody();

		// Valida que el subject (username) del token sea el esperado.
		assertEquals(username, claims.getSubject());

		// Valida que el token contenga el rol esperado.
		List<String> authorities = (List<String>) claims.get("authorities");
		assertTrue(authorities.contains("ROLE_USER"));

		// Verifica que el token no haya expirado.
		assertTrue(claims.getExpiration().after(new Date()));
	}
}
