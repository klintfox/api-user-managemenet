package com.bci.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = -6499381132235469726L;

	private String mensaje;

	@JsonProperty("id")
	private UUID id;

	@JsonProperty("created")
	private Date created;

	@JsonProperty("modified")
	private Date modified;

	@JsonProperty("last_login")
	private Date lastLogin;

	@JsonProperty("token")
	private String token;

	@JsonProperty("isactive")
	private String isactive;

	public ResponseDto() {
	}

	public ResponseDto(String mensaje, UUID id, Date created, Date modified, Date lastLogin, String token,
			String isactive) {
		super();
		this.mensaje = mensaje;
		this.id = id;
		this.created = created;
		this.modified = modified;
		this.lastLogin = lastLogin;
		this.token = token;
		this.isactive = isactive;
	}

	public ResponseDto(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "ResponseDto [mensaje=" + mensaje + ", id=" + id + ", created=" + created + ", modified=" + modified
				+ ", lastLogin=" + lastLogin + ", token=" + token + ", isactive=" + isactive + "]";
	}

}