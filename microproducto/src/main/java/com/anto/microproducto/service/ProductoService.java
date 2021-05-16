package com.anto.microproducto.service;

import java.util.List;
import java.util.Optional;

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
		Optional<Producto> optProducto = productoRepo.findById(idProducto);

		producto.setCodigoId(optProducto.get().getCodigoId());
		producto.setDescripcion(optProducto.get().getDescripcion());
		producto.setMarca(optProducto.get().getMarca());
		producto.setNombre(optProducto.get().getNombre());
		producto.setPrecioUnitario(optProducto.get().getPrecioUnitario());
		producto.setUnidades(optProducto.get().getUnidades());
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
