package com.anto.microproducto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anto.microproducto.model.Producto;
import com.anto.microproducto.service.IProducto;

@RestController
public class ProductoController {
	
	@Autowired
	IProducto productoSvc;
	
	@Autowired
	Environment env;
	
	@GetMapping("/productos")
	public List<Producto> getListProductos(){
		return productoSvc.listAllProductos();
	}
	
	@GetMapping("/producto/{idProducto}")
	public Producto getProducto(@PathVariable String idProducto){
		return productoSvc.getProductoById(idProducto);
	}

	@DeleteMapping("/producto/{idProducto}")
	public void deleteProducto(@PathVariable String idProducto){
		productoSvc.deleteProductoById(idProducto);
	}
	
	@PostMapping("/producto")
	public void addProducto(@ModelAttribute Producto producto) {
		productoSvc.addProducto(producto);
	}
}
