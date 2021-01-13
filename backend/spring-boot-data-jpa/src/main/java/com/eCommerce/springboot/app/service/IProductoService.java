package com.eCommerce.springboot.app.service;

import java.util.List;

import com.eCommerce.springboot.app.model.dto.ProductoModelo;
import com.eCommerce.springboot.app.model.entity.Producto;
public interface IProductoService {

	/* public List<Producto> findByNombre(String term); */

	public Producto findById(Long id);

	public List<Producto> findAllProduct();
	
	public ProductoModelo findProductoById(Long id);
	
	public Integer findProductoCantidadByProductoId(Long id);

	public Integer updateProductQuantityById(Integer cantidad, Long id);
	
	public String findProductonameById(Long id);
	
}
