package utils;

import java.util.ArrayList;

public class NodoEstado {
	private int color;
	private int id;
	private boolean esNegado;
	private ArrayList<NodoEstado> nextNodos;
	
	public NodoEstado(int id, int c, boolean negado) {
		nextNodos  = new ArrayList<NodoEstado>();
		this.id    = id;
		this.color = c;
		this.esNegado = negado;
	}
	
	public void connect(NodoEstado n) {
		nextNodos.add(n);
	}
	
	public boolean getNegado() {
		return this.esNegado; 
	}

	public int getColor() {
		return this.color;
	}

	public int getId() {
		return this.id;
	}
	
	public ArrayList<NodoEstado> getAdyacentes() {
		return this.nextNodos;
	}
}
