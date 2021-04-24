package com.anto.microcarrito.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anto.microcarrito.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito,Integer>{

	@Query(value="select * from cesta where cesta.usuario like ?1 ", nativeQuery = true) 
	public List<Carrito> findByNombre(String username);
	

}
