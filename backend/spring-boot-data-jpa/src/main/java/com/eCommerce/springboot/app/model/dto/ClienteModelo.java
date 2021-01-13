package com.eCommerce.springboot.app.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.eCommerce.springboot.app.model.entity.Cliente;

public class ClienteModelo implements Serializable{

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String dni;
	private String celular;
	private Date fnac;
	
	public ClienteModelo() {
		
	}
	
	public ClienteModelo(Cliente cliente) {
		this.id=cliente.getId();
		this.nombre=cliente.getNombre();
		this.apellido=cliente.getApellido();
		this.dni= cliente.getDni();
		this.celular= cliente.getCelular();
		this.fnac=cliente.getFnac();
		this.email=cliente.getEmail();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getFnac() {
		return fnac;
	}

	public void setFnac(Date fnac) {
		this.fnac = fnac;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
