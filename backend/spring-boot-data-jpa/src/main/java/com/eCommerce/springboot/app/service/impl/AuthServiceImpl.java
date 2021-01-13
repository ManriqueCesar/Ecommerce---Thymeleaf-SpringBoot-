package com.eCommerce.springboot.app.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eCommerce.springboot.app.dao.IUsuarioDao;
import com.eCommerce.springboot.app.model.dto.LoginResponseModel;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;
import com.eCommerce.springboot.app.security.JwtTokenUtil;
import com.eCommerce.springboot.app.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

	
	private final Logger LOGGER = Logger.getLogger(AuthServiceImpl.class.getName());
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public void authenticate(String username, String password) throws DisabledException,BadCredentialsException {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	@Override
	public LoginResponseModel login(UsuarioModelo usuario) {
		try {
			authenticate(usuario.getUsuario(), usuario.getPassword());
			return new LoginResponseModel(jwtTokenUtil.generateToken(usuario),usuarioDao.findByUsuario(usuario.getUsuario()), "Sesion iniciada", HttpStatus.OK);
		} catch (DataAccessException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return new LoginResponseModel(null, null, "Error al realizar la consulta en la base de datos",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (DisabledException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return new LoginResponseModel(null,null, "El usuario está deshabilitado", HttpStatus.UNAUTHORIZED);
		}catch (BadCredentialsException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return new LoginResponseModel(null,null, "El usuario o contraseña son incorrectos", HttpStatus.UNAUTHORIZED);
		}catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return new LoginResponseModel(null,null, "Ocurrió un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
