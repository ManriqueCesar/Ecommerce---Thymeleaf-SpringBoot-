package com.eCommerce.springboot.app.service;

import com.eCommerce.springboot.app.model.dto.LoginResponseModel;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;

public interface IAuthService {
	
	public LoginResponseModel login(UsuarioModelo usuario);
}
