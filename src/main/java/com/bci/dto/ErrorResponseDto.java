package com.bci.dto;

public class ErrorResponseDto {

	private String mensaje;

	public ErrorResponseDto(String key, String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
