package utils;

import java.util.ArrayList;
import java.util.List;


public class Materia {
	private int id;
	private ArrayList<NodoEstado> estados;
	private ArrayList<Materia> adyacentes;
	private ArrayList<Integer> colores;

	public Materia(List<Integer> nuevosColores) {
		colores    = new ArrayList<Integer>();
		adyacentes = new ArrayList<Materia>();
		
		for (Integer i : nuevosColores) {
			this.colores.add(i);
		}		
	}
	
	public void addAdyacente(Materia m) {
		adyacentes.add(m);
	}
	
	public void addEstados(ArrayList<NodoEstado> estados) {
		this.estados = estados;
	}
	
	public void setColor(Integer i) {
		colores.add(i);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getColor(int i) {
		return colores.get(i);
	}
	
	public ArrayList<Integer> getColores() {
		return colores;
	}

	public List<Materia> getAdyacentes() {
		return this.adyacentes;
	}
	
	public NodoEstado getEstado(int i) {
		return estados.get(i);
	}
	
	
}