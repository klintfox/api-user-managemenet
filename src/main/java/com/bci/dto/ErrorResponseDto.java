package com.bci.dto;

import java.io.Serializable;

public class ErrorResponseDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
