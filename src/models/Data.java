package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853128693661403146L;
	Loteria loteria;
	ArrayList<Apuesta> apuestas;
	
	public Data(){
		super();
		this.loteria = new Loteria();
		this.apuestas = new ArrayList<Apuesta>();
	}
	
	public void setLoteria(Loteria loteria){
		this.loteria = loteria;
	}
	public Loteria getLoteria(){
		return this.loteria;
	}
	public void setApuesta(int i, Apuesta apuesta){
		this.apuestas.set(i, apuesta);
	}
	public Apuesta getApuesta(int i){
		return this.apuestas.get(i);
	}
	public void setApuestas(ArrayList<Apuesta> apuestas){
		this.apuestas = apuestas;
	}
	public ArrayList<Apuesta> getApuestas(){
		return this.apuestas;
	}
	public double totalApostado(int i){
		double acum=0;
		for(Apuesta apuesta: apuestas)
			if (apuesta.getFicha().getNumero() == i)
				acum+=apuesta.getMonto();
		return acum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int cantidadTickets(int i){
		int cont=0;
		for(Apuesta apuesta: apuestas)
			if (apuesta.getFicha().getNumero() == i)
				cont++;
		return cont;
	}
}
