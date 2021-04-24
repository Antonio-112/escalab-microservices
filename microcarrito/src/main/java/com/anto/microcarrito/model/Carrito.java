package com.anto.microcarrito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cesta")
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String marca;
	@Column(name="producto")
	private String nombreProducto;
	@Column(name="prec_tot")
	private Integer precioTotal;
	@Column(name="pre_uni")
	private Integer precioUnitario;
	private Integer unidades;
	private String usuario;
	
}
