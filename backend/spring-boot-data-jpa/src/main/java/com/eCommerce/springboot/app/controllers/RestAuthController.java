package com.eCommerce.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.springboot.app.model.dto.LoginResponseModel;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;
import com.eCommerce.springboot.app.service.IAuthService;

@RestController
@RequestMapping("/auth")
public class RestAuthController {
	
	@Autowired
	private IAuthService authService;

	@PostMapping
	public ResponseEntity<LoginResponseModel> login(@RequestBody UsuarioModelo usuario) {
		LoginResponseModel response = authService.login(usuario);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
}
