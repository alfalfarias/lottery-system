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
	public void setApuestas(ArrayList<Apuesta> apuestas){
		this.apuestas = apuestas;
	}
	public ArrayList<Apuesta> getApuestas(){
		return this.apuestas;
	}
}
