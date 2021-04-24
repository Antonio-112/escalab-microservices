package com.anto.microproducto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.anto.microproducto.dao.ProductoRepository;
import com.anto.microproducto.model.Producto;

@Service
public class ProductoService implements IProducto {

	@Autowired
	ProductoRepository productoRepo;
	
	@Autowired
	Environment env;
	
	@Override
	public List<Producto> listAllProductos() {
		return productoRepo.findAll();
	}

	@Override
	public Producto getProductoById(String idProducto) {
		Producto producto = new Producto();
		producto.setCodigoId(productoRepo.findById(idProducto).get().getCodigoId());
		producto.setDescripcion(productoRepo.findById(idProducto).get().getDescripcion());
		producto.setMarca(productoRepo.findById(idProducto).get().getMarca());
		producto.setNombre(productoRepo.findById(idProducto).get().getNombre());
		producto.setPrecioUnitario(productoRepo.findById(idProducto).get().getPrecioUnitario());
		producto.setUnidades(productoRepo.findById(idProducto).get().getUnidades());
		producto.setPort(env.getProperty("local.server.port"));
		return producto;
	}

	@Override
	public void deleteProductoById(String idProducto) {
		productoRepo.deleteById(idProducto);
	}

	@Override
	public void addProducto(Producto producto) {
		productoRepo.save(producto);
	}

}
