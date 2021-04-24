package com.anto.microproducto.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anto.microproducto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,String> {	
	

}
