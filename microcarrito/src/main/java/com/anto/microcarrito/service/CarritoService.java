package com.anto.microcarrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anto.microcarrito.dao.CarritoRepository;
import com.anto.microcarrito.model.Carrito;
import com.anto.microcarrito.model.Producto;

@Service
public class CarritoService implements ICarrito {

	//private static final Logger log = LoggerFactory.getLogger(CarritoService.class);

	private static final String SERVICIO_PRODUCTO = "PRODUCTO.SERVICE";
	
	private static final String SERVICIO_USER= "MICRO.UAA";

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CarritoRepository carritoRepo;

	@Override
	public List<Carrito> getCarrito(String username) {
		return carritoRepo.findByNombre(username);
	}
	
	@Override
	public void addProductoCarrito(String idProducto, Integer unidades, String auth) {
		
		Producto producto = getProducto(idProducto, auth);
		String username = getUsuario(auth);
		
		Carrito carrito = new Carrito(null,
				producto.getMarca(),
				producto.getNombre(),
				(Integer) (producto.getPrecioUnitario() * unidades),
				producto.getPrecioUnitario(),
				unidades,
				username);
		
		carritoRepo.saveAndFlush(carrito);
	}

	
	@Override
	public Producto getProducto(String codigo, String auth) {
		
		// Encabezado de Authentication
		 HttpHeaders headers = new HttpHeaders();
		 headers.set("Authorization",auth);
		 HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		// Conexion HTTP enviando encabezado
		 ResponseEntity<Producto> resp =
		 restTemplate.exchange("http://"+SERVICIO_PRODUCTO+"/producto/"+codigo,
		 HttpMethod.GET, entity, Producto.class);
		return resp.getBody();
	}

	@Override
	public void deleteCarritoFromId(Integer id) {
		carritoRepo.deleteById(id);	
	}

	@Override
	public String getUsuario(String auth) {
				// Encabezado de Authentication
				 HttpHeaders headers = new HttpHeaders();
				 headers.set("Authorization",auth);
				 HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

				// Conexion HTTP enviando encabezado
				 ResponseEntity<String> resp =
				 restTemplate.exchange("http://"+SERVICIO_USER+"/username",
				 HttpMethod.GET, entity, String.class);

			return resp.getBody();
	}

	
}
