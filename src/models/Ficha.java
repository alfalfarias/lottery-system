package models;

import java.io.Serializable;

public class Ficha implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 809186458887513528L;
	int numero;
	String nombre;
	public Ficha(int numero, String nombre) {
		super();
		this.numero = numero;
		this.nombre = nombre;
	}
	public Ficha(){
		super();
		this.nombre = new String();
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
