package utils;


import java.util.ArrayList;
import java.util.List;

import ejercicio1.NodoEstado;

public class Materia {
	private int id;
	private ArrayList<Materia> adyacentes;
	private ArrayList<Integer> colores;
	
	public Materia(List<Integer> colores) {
		for (Integer i : colores) {
			this.colores.add(i);
		}		
		
		adyacentes = new ArrayList<Materia>();
	}
	
	public void addAdyacente(Materia m) {
		adyacentes.add(m);
	}
	
	public int getC1() {
		return c1;
	}
	
	public int getC2() {
		return c2;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public List<Materia> getAdyacentes() {
		return this.adyacentes;
	}
}