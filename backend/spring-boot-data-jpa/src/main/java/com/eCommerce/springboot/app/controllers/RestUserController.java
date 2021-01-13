package com.eCommerce.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.springboot.app.model.dto.LoginResponseModel;
import com.eCommerce.springboot.app.model.dto.ResponseModel;
import com.eCommerce.springboot.app.model.dto.UsuarioClienteModelo;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;
import com.eCommerce.springboot.app.service.IUsuarioService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/usuario")
public class RestUserController {

	@Autowired
	private IUsuarioService usuarioService;

	
	@GetMapping
	public List<UsuarioModelo> findAll() {
		return usuarioService.findAll();
	}

	
	@PostMapping("/signUp")
	public ResponseEntity<ResponseModel> create(@RequestBody UsuarioClienteModelo usuario) {
		ResponseModel response = usuarioService.createUsuario(usuario);
		return new ResponseEntity<>(response, response.getHttpStatus());

	
	}

}
