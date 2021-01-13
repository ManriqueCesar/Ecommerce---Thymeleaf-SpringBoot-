package com.eCommerce.springboot.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eCommerce.springboot.app.dao.ICarritoDao;
import com.eCommerce.springboot.app.dao.IClienteDao;
import com.eCommerce.springboot.app.dao.IUsuarioDao;
import com.eCommerce.springboot.app.model.dto.LoginResponseModel;
import com.eCommerce.springboot.app.model.dto.ResponseModel;
import com.eCommerce.springboot.app.model.dto.UsuarioClienteModelo;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;
import com.eCommerce.springboot.app.model.entity.Carrito;
import com.eCommerce.springboot.app.model.entity.Cliente;
import com.eCommerce.springboot.app.model.entity.Usuario;
import com.eCommerce.springboot.app.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private final Logger LOGGER = Logger.getLogger(UsuarioServiceImpl.class.getName());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	private ICarritoDao carritoDao;

	@Override
	public List<UsuarioModelo> findAll() {
		return usuarioDao.findAllUsuarios();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResponseModel createUsuario(UsuarioClienteModelo usuarioModelo) {

		try {

			if ( (clienteDao.findByEmail( usuarioModelo.getEmail() ) != null )) {
				return new ResponseModel("El correo ya existe en la base de datos", HttpStatus.UNPROCESSABLE_ENTITY);
				
			} else if (usuarioDao.findByUsuario( usuarioModelo.getUsuario() )  != null ) {
				return new ResponseModel("El usuario ya existe en la base de datos", HttpStatus.UNPROCESSABLE_ENTITY);
				
				} else {
						Usuario user = new Usuario();
						Cliente cliente = new Cliente();
						Boolean estado = true;
						
						//carrito
						Carrito carro = new Carrito();
						
						cliente.setNombre(usuarioModelo.getNombre());
						cliente.setApellido(usuarioModelo.getApellido());
						cliente.setEmail(usuarioModelo.getEmail());
						cliente.setDni(usuarioModelo.getDni());
						cliente.setFnac(usuarioModelo.getFnac());
						cliente.setCelular(usuarioModelo.getCelular());
						clienteDao.save(cliente);
					
						carro.setCliente(cliente);
						
						carritoDao.save(carro);
					
						user.setUsuario(usuarioModelo.getUsuario());
						user.setPassword(passwordEncoder.encode(usuarioModelo.getPassword()));
						user.setRol(usuarioModelo.getRol());
						user.setEnabled(estado);
						user.setCliente(cliente);
		
						usuarioDao.save(user);
		
						return new ResponseModel("Usuario creado correctamente", HttpStatus.CREATED);
						}


		} catch(Exception e){
		LOGGER.log(Level.SEVERE, e.getMessage(), e);
		return new ResponseModel("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

}
