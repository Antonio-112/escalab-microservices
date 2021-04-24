package com.anto.microproducto.service;

import java.util.List;

import com.anto.microproducto.model.Producto;

public interface IProducto {

	void addProducto(Producto producto);
	List<Producto> listAllProductos();
	Producto getProductoById(String idProducto);
	void deleteProductoById(String idProducto);
}
