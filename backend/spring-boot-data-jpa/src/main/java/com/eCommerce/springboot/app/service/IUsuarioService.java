package com.eCommerce.springboot.app.service;

import java.util.List;

import com.eCommerce.springboot.app.model.dto.LoginResponseModel;
import com.eCommerce.springboot.app.model.dto.ResponseModel;
import com.eCommerce.springboot.app.model.dto.UsuarioClienteModelo;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;
import com.eCommerce.springboot.app.model.entity.Usuario;

public interface IUsuarioService {

	
	public List<UsuarioModelo> findAll();
	
	public ResponseModel createUsuario( UsuarioClienteModelo usuario);
	
}
