package com.eCommerce.springboot.app.service;

import java.util.List;

import com.eCommerce.springboot.app.model.dto.CarritoModelo;
import com.eCommerce.springboot.app.model.dto.CarritoProductoModelo;
import com.eCommerce.springboot.app.model.dto.ResponseModel;
import com.eCommerce.springboot.app.model.entity.Carrito;

public interface ICarritoService {

	public Carrito saveCarrito(CarritoProductoModelo carrito);
	
	public ResponseModel deleteCarrito(Long id);
	
	public ResponseModel llenarCarrito(CarritoProductoModelo carrito) ;
	
	public CarritoModelo findCarritoById(Long id);

	public List<CarritoModelo> findAll();
	
	public List<CarritoProductoModelo> findAllCarritoProducto();
	
	public List<CarritoProductoModelo> findCarritoDetalleById (Long id);

	

}
	