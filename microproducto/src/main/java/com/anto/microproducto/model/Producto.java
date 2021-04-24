package com.anto.microproducto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
	
	@Id
	private String codigoId;
	private String nombre;
	private String descripcion;
	private String marca;
	private Integer precioUnitario;
	private Integer unidades;
	@Transient
	private String port;

}
