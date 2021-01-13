package com.eCommerce.springboot.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eCommerce.springboot.app.model.entity.Carrito;
import com.eCommerce.springboot.app.model.entity.CarritoProducto;


public interface ICarritoProductoDao extends JpaRepository<CarritoProducto, Long> {
	

}
