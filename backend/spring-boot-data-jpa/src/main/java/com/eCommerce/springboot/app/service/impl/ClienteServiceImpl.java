package com.eCommerce.springboot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eCommerce.springboot.app.dao.IClienteDao;
import com.eCommerce.springboot.app.model.dto.ClienteModelo;
import com.eCommerce.springboot.app.model.entity.Cliente;
import com.eCommerce.springboot.app.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;


	@Override
	@Transactional(readOnly = true)
	public List<ClienteModelo> findAll() {
		return (List<ClienteModelo>) clienteDao.findAllClientes();
	}

	@Transactional
	public Cliente save(ClienteModelo clienteModelo) {
		return clienteDao.save(new Cliente(clienteModelo));

	}

	@Override
	public ClienteModelo findOne(Long id) {
		return clienteDao.findClienteById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public ClienteModelo findClienteById(Long id) {
		return clienteDao.findClienteById(id);
	}

}
