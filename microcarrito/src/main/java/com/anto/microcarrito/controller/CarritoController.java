package com.anto.microcarrito.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anto.microcarrito.model.Carrito;
import com.anto.microcarrito.model.Producto;
import com.anto.microcarrito.service.ICarrito;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author Antonio A.
 * @version 05/04/2021-V1.0
 * 
 */

@RestController
public class CarritoController {

	private static final Logger log = LoggerFactory.getLogger(CarritoController.class);

	@Autowired
	ICarrito carritoSvc;
	

	@GetMapping("/carrito/{username}")
	@ResponseStatus(HttpStatus.OK)
	public List<Carrito> getCarritoByUsername(@PathVariable String username) {
		return carritoSvc.getCarrito(username);
	}

	@GetMapping(value = "/producto/{idProducto}")
	@ResponseStatus(HttpStatus.OK)
	@HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public Producto getProducto(@PathVariable String idProducto, @RequestHeader("Authorization") String auth) {
		return carritoSvc.getProducto(idProducto, auth);
	}

	@PostMapping(value = "/carrito/{idProducto}/{unidades}")
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000") })
	public void addProductoCarrito(@PathVariable String idProducto, @PathVariable Integer unidades,
			@RequestHeader("Authorization") String auth) {

		carritoSvc.addProductoCarrito(idProducto, unidades, auth);
	}

	@DeleteMapping(value = "/carrito/{idCarrito}")
	public void deleteCarritoFromId(@PathVariable Integer idCarrito) {
		carritoSvc.deleteCarritoFromId(idCarrito);
	}

	// *******************************FALLBACKS************************************//
	@HystrixCommand
	private Producto fallbackMethod(String idProducto, String auth) {
		log.error(String.format("Error en conexion a productos"));
		Producto producto = new Producto("0", "0", "0", "0", 0, 0, "0");
		return producto;
	}

	@HystrixCommand
	private Producto fallBackMethod(String idProducto, Integer unidades, String auth) {
		log.error(String.format("Error en connexion a productos"));
		Producto producto = new Producto("0", "0", "0", "0", 0, 0, "0");
		return producto;

	}
	// *******************************FALLBACKS************************************//

}
