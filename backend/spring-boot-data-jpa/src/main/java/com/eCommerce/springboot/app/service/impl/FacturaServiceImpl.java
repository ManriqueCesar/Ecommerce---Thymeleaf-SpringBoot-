package com.eCommerce.springboot.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eCommerce.springboot.app.dao.IFacturaDao;
import com.eCommerce.springboot.app.model.dto.FacturaModelo;
import com.eCommerce.springboot.app.model.dto.FacturaProductoModelo;
import com.eCommerce.springboot.app.model.dto.ItemFacturaModelo;
import com.eCommerce.springboot.app.model.dto.ResponseModel;
import com.eCommerce.springboot.app.model.dto.ResponseModelArray;
import com.eCommerce.springboot.app.model.entity.Cliente;
import com.eCommerce.springboot.app.model.entity.Factura;
import com.eCommerce.springboot.app.model.entity.ItemFactura;
import com.eCommerce.springboot.app.service.IFacturaService;
import com.eCommerce.springboot.app.service.IProductoService;

@Service
public class FacturaServiceImpl implements IFacturaService {

	private final Logger LOGGER = Logger.getLogger(CarritoServiceImpl.class.getName());

	@Autowired
	private IFacturaDao facturaDao;

	@Autowired
	private IProductoService productoService;

	
	@Override
	@Transactional(readOnly = true)
	public List<FacturaModelo> findAllFactura() {
		// TODO Auto-generated method stub
		return (List<FacturaModelo>) facturaDao.findAllFacturas();
	}

	@Override
	@Transactional
	public ResponseModel saveFactura(FacturaProductoModelo facModelo) {

		try {
			Factura factura = new Factura();
			Cliente cliente = new Cliente();

			factura.setDescripcion(facModelo.getDescripcion());

			factura.setObservacion(facModelo.getObservacion());

			cliente.setId(facModelo.getIdCliente());

			factura.setCliente(cliente);

			List<ItemFactura> items = new ArrayList<>();
			List<String> prod = new ArrayList<String>();

			

			for (ItemFacturaModelo itemFacturaModelo : facModelo.getItems()) {

				ItemFactura itemFactura = new ItemFactura();

				Long productoId = itemFacturaModelo.getProductoId(); 

				Integer stockProducto = productoService.findProductoCantidadByProductoId(productoId);

				itemFactura.setProducto(productoService.findById(productoId));

				itemFactura.setCantidad(itemFacturaModelo.getCantidad());

				items.add(itemFactura);

				if (itemFacturaModelo.getCantidad() > stockProducto) {

					String productoNombre = productoService.findProductonameById(productoId);
					prod.add(productoNombre);
					
				} else if (prod.isEmpty()){

					Integer nuevaCantidad = (int) (stockProducto - itemFacturaModelo.getCantidad());

					productoService.updateProductQuantityById(nuevaCantidad, itemFacturaModelo.getProductoId());
				}
			}

			if (prod.isEmpty()) {
				factura.setItems(items);
				facturaDao.save(factura);

				return new ResponseModel("Factura agregada correctamente", HttpStatus.CREATED);
				
			} else {
				
				return new ResponseModelArray("Error al crear factura, no hay stock de producto(s): ",
						HttpStatus.INTERNAL_SERVER_ERROR, prod);
				
			}


		} catch (Exception e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);

			return new ResponseModel("Error al crear factura", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWhithItemFacturaWithProducto(id);
	}

	@Override
	public FacturaModelo findFacturaById(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findFacturaById();
	}

	@Override
	public Factura findFacById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura findDetalleById(Long id) {
		return facturaDao.findDetalleById(id);
	}

}
