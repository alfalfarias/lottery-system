package models;

import java.util.ArrayList;

public class Loteria {
	int apuesta;
	double ganancia;
	double relacion;
	ArrayList<Ficha> fichas = new ArrayList<Ficha>();
	public Loteria(int apuesta, double ganancia, ArrayList<Ficha> fichas) {
		super();
		this.apuesta = apuesta;
		this.ganancia = ganancia;
		this.fichas = fichas;
	}
	public int getApuesta() {
		return apuesta;
	}
	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}
	public double getGanancia() {
		return ganancia;
	}
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}
	public ArrayList<Ficha> getFichas() {
		return fichas;
	}
	public void setFichas(ArrayList<Ficha> fichas) {
		this.fichas = fichas;
	}
	public double getRelacion() {
		return relacion;
	}
	public void setRelacion(double relacion) {
		this.relacion = relacion;
	}
}
