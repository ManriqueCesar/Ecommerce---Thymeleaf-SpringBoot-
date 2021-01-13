package com.eCommerce.springboot.app.model.dto;

import java.util.Date;

import com.eCommerce.springboot.app.model.entity.Usuario;

public class UsuarioClienteModelo {

	private Long id;
	private String nombre;
	private String apellido;
	private String dni;
	private Date fnac;
	private String celular;
	private String email;
	private String usuario;
	private String password;
	private String rol;
	
	public UsuarioClienteModelo ( Usuario user ) {
		this.id= user.getCliente().getId();
		this.nombre=user.getCliente().getNombre();
		this.apellido=user.getCliente().getApellido();
		this.email=user.getCliente().getEmail();
		this.usuario = user.getUsuario();
		this.password = user.getPassword();
		this.rol=user.getRol();
	}

	public UsuarioClienteModelo () {
		
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFnac() {
		return fnac;
	}

	public void setFnac(Date fnac) {
		this.fnac = fnac;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
}
