package com.eCommerce.springboot.app.model.dto;

import com.eCommerce.springboot.app.model.entity.Usuario;

public class UsuarioModelo {

	private Long id;
	private String nombre;
	private String apellido;
	private String usuario;
	private String password;
	private boolean enabled;

	public UsuarioModelo(Usuario user) {

		this.id = user.getId();
		this.nombre = user.getCliente().getNombre();
		this.apellido = user.getCliente().getApellido();
		this.usuario = user.getUsuario();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
	}

	public UsuarioModelo() {

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
