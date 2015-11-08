package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NodoMateria implements java.io.Serializable{
	private int id;
	private ArrayList<NodoEstado> estados;
	private ArrayList<NodoMateria> adyacentes;
	private ArrayList<Integer> colores;
	private ArrayList<Integer> coloresPosibles;
	
	public NodoMateria(List<Integer> nuevosColores) {
		colores    = new ArrayList<Integer>();
		adyacentes = new ArrayList<NodoMateria>();
		coloresPosibles = new ArrayList<Integer>();
		
		for (Integer i : nuevosColores) {
			this.coloresPosibles.add(i);
		}		
	}
	public NodoMateria(int c1, int c2, int c3, int c4, int c5) {
		colores    = new ArrayList<Integer>();
		adyacentes = new ArrayList<NodoMateria>();
		coloresPosibles = new ArrayList<Integer>();
		this.colores.add(c1);
		this.colores.add(c2);
		this.colores.add(c3);
		this.colores.add(c4);
		this.colores.add(c5);
	}
	
	public NodoMateria(int i) {
		colores    = new ArrayList<Integer>();
		adyacentes = new ArrayList<NodoMateria>();
		coloresPosibles = new ArrayList<Integer>();
		Random randomGenerator = new Random();
		
		for(int j = 0; j < randomGenerator.nextInt(4) + 1 ; j++){
			this.colores.add(j);
		}
		this.colores.add(i);
	}
	public NodoMateria(int i, boolean k) {
		colores    = new ArrayList<Integer>();
		adyacentes = new ArrayList<NodoMateria>();
		coloresPosibles = new ArrayList<Integer>();
		Random randomGenerator = new Random();
		
		
		for(int j = 1; j < 4; j++){
			this.coloresPosibles.add(j);
		}
		this.coloresPosibles.add(i);
		
	}
	
	public NodoMateria(int i, int j) {
		colores    = new ArrayList<Integer>();
		adyacentes = new ArrayList<NodoMateria>();
		coloresPosibles = new ArrayList<Integer>();
		this.colores.add(i);
		this.colores.add(j);
	}
	public void addAdyacente(NodoMateria m) {
		this.adyacentes.add(m);
	}
	
	public void addEstados(ArrayList<NodoEstado> estados) {
		this.estados = estados;
	}
	
	public void setColor(Integer i) {
		this.colores.add(i);
	}
	
	public void clearColors(){
		this.colores = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getColoresPosibles() {
		return this.coloresPosibles;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getColor(int i) {
		return colores.get(i);
	}
	
	public ArrayList<Integer> getColores() {
		return this.colores;
	}

	public List<NodoMateria> getAdyacentes() {
		return this.adyacentes;
	}
	
	public NodoEstado getEstado(int i) {
		return estados.get(i);
	}
	
	
}