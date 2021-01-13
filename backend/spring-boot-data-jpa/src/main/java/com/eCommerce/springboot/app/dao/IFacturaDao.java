package com.eCommerce.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.eCommerce.springboot.app.model.dto.FacturaModelo;
import com.eCommerce.springboot.app.model.dto.FacturaProductoModelo;
import com.eCommerce.springboot.app.model.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{
	
	@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1")
	public Factura fetchByIdWithClienteWhithItemFacturaWithProducto(Long id);
	
	@Query("select new com.eCommerce.springboot.app.model.dto.FacturaModelo(c) from Factura c")
	public List<FacturaModelo> findAllFacturas();
	
	@Query("select new com.eCommerce.springboot.app.model.dto.FacturaModelo(f) from Factura f where f.id=:id")
	public FacturaModelo findFacturaById();

	public Factura save(FacturaProductoModelo factura);
	
	@Query("select i from ItemFactura i join fetch i.producto where i.id=:id")
	public Factura findDetalleById(Long id);
}

