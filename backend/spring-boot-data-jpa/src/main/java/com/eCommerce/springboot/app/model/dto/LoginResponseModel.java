package com.eCommerce.springboot.app.model.dto;

import org.springframework.http.HttpStatus;

import com.eCommerce.springboot.app.model.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginResponseModel {
	
	private String token;
	private String message;
	private UsuarioModelo usuario;
	
	@JsonIgnore
	private HttpStatus httpStatus;
	
	public LoginResponseModel(String token ,UsuarioModelo user, String message, HttpStatus httpStatus) {
		this.usuario=user;
		this.message = message;
		this.httpStatus = httpStatus;
		this.token= token;
	}

	
	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}




	public UsuarioModelo getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioModelo usuario) {
		this.usuario = usuario;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
