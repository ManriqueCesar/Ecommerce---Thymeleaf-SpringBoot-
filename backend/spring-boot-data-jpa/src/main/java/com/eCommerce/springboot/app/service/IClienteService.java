package com.eCommerce.springboot.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eCommerce.springboot.app.model.dto.ClienteModelo;
import com.eCommerce.springboot.app.model.entity.Cliente;
public interface IClienteService {

	// Clientes
	
	public List<ClienteModelo> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public ClienteModelo findOne(Long id);
	
	public ClienteModelo findClienteById(Long id);
	
	/*public Cliente fetchByIdWithFacturas(Long id);*/
	
	public Cliente save(ClienteModelo cliente);

	public void delete(Long id);
	

}
