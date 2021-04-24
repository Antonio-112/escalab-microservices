package com.anto.microcarrito.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
	
	private String codigoId;
	private String nombre;
	private String descripcion;
	private String marca;
	private Integer precioUnitario;
	private Integer unidades;
	private String port;
}
