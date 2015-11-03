package utils;

import java.util.ArrayList;

public class NodoEstado {
	private int color;
	private int id;
	private int idPadre;
	private boolean esNegado;
	private ArrayList<NodoEstado> nextNodos;
	
	public NodoEstado(int id, int idPadre, int c, boolean negado) {
		this.nextNodos = new ArrayList<NodoEstado>();
		this.esNegado = negado;
		this.idPadre  = idPadre;
		this.id    = id;
		this.color = c;
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

	public int getPadreId() {
		return this.idPadre;
	}
	
	public ArrayList<NodoEstado> getAdyacentes() {
		return this.nextNodos;
	}
}
