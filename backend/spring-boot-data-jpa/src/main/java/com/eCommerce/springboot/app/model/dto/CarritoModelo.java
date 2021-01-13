package com.eCommerce.springboot.app.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.eCommerce.springboot.app.model.entity.Carrito;

public class CarritoModelo implements Serializable{
	
	private Long id;
	private Date create_at;

	public CarritoModelo() {
		
	}
	
	public CarritoModelo(Carrito carro) {
			this.id =carro.getCarritoId();
			this.create_at=carro.getCreateAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
