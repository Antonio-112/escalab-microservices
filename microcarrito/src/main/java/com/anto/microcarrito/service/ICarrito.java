package com.anto.microcarrito.service;

import java.util.List;

import com.anto.microcarrito.model.Carrito;
import com.anto.microcarrito.model.Producto;

public interface ICarrito {

	List<Carrito> getCarrito(String username);
	Producto getProducto(String idProducto, String auth);
	String getUsuario(String auth);
	void addProductoCarrito(String idProducto, Integer unidades, String auth);
	void deleteCarritoFromId(Integer id);
	
}
