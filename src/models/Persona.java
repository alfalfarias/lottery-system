package models;

import java.io.Serializable;

public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3743765693941408746L;
	String cedula = new String();
	String nombres = new String();
	String apellidos = new String();
	public Persona(String cedula, String nombres, String apellidos) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
	}
	Persona(){
		super();
		this.cedula = new String();
		this.nombres = new String();
		this.apellidos = new String();
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}
