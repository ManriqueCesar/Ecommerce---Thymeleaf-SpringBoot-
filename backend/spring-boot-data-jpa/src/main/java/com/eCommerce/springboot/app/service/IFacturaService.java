package com.eCommerce.springboot.app.service;

import java.util.List;

import com.eCommerce.springboot.app.model.dto.FacturaModelo;
import com.eCommerce.springboot.app.model.dto.FacturaProductoModelo;
import com.eCommerce.springboot.app.model.dto.ResponseModel;
import com.eCommerce.springboot.app.model.entity.Factura;


public interface IFacturaService {
	//Factura
	
	public ResponseModel saveFactura(FacturaProductoModelo factura);
	
	public Factura findFacById(Long id);
	
	public FacturaModelo findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id);

	public List<FacturaModelo> findAllFactura();
	
	public Factura findDetalleById(Long id);
	
}
