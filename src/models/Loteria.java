package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Loteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1681469151721454253L;
	double ganancia=1;
	ArrayList<Ficha> fichas;
	Ficha fichaGanador = new Ficha();
	public Loteria(double ganancia, ArrayList<Ficha> fichas) {
		super();
		this.ganancia = ganancia;
		this.fichas = fichas;
	}
	public Loteria(){
		super();
		this.fichas = new ArrayList<Ficha>();
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
	public Ficha getFicha(int i) {
		return fichas.get(i);
	}
	public void setFicha(int i, Ficha ficha) {
		this.fichas.set(i, ficha);
	}
	public Ficha getFichaGanador() {
		return fichaGanador;
	}
	public void setFichaGanador(Ficha fichaGanador) {
		this.fichaGanador = fichaGanador;
	}
}
