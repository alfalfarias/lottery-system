package models;

public class Apuesta {
	int id;
	Ficha ficha;
	Persona persona;
	double monto;
	String estado;
	public Apuesta(int id, Ficha ficha, Persona persona, double monto,
			String estado) {
		super();
		this.id = id;
		this.ficha = ficha;
		this.persona = persona;
		this.monto = monto;
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ficha getFicha() {
		return ficha;
	}
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
