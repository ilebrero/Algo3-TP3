package utils;

import java.util.ArrayList;
import java.util.List;

public class NodoMateria {
	private int id;
	private ArrayList<NodoEstado> estados;
	private ArrayList<NodoMateria> adyacentes;
	private ArrayList<Integer> colores;

	public NodoMateria(List<Integer> nuevosColores) {
		colores    = new ArrayList<Integer>();
		adyacentes = new ArrayList<NodoMateria>();
		
		for (Integer i : nuevosColores) {
			this.colores.add(i);
		}		
	}
	
	public void addAdyacente(NodoMateria m) {
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

	public List<NodoMateria> getAdyacentes() {
		return this.adyacentes;
	}
	
	public NodoEstado getEstado(int i) {
		return estados.get(i);
	}
	
	
}