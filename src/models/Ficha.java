package models;

public class Ficha {
	int numero;
	String nombre;
	public Ficha(int numero, String nombre) {
		super();
		this.numero = numero;
		this.nombre = nombre;
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
