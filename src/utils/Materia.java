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
		return colores.get(0);
	}
	
	public int getC2() {
		return colores.get(1);
	}
	public ArrayList<Integer> getColores() {
		return colores;
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

	public void setColor(Integer integer) {
		// TODO Auto-generated method stub
		
	}
}