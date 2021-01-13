package com.eCommerce.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eCommerce.springboot.app.model.dto.ClienteModelo;
import com.eCommerce.springboot.app.model.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	@Query("select new com.eCommerce.springboot.app.model.dto.ClienteModelo(c) from Cliente c where c.id=:id")
	public ClienteModelo findClienteById(@Param("id") Long id);
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWithFacturas(Long id);

	
	@Query("select new com.eCommerce.springboot.app.model.dto.ClienteModelo(c) from Cliente c")
	public List<ClienteModelo> findAllClientes();
	
	public Cliente save(ClienteModelo cliente);
	
	public Cliente findByEmail(String string);
	
	//usuario

}
