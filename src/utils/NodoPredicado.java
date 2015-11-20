package utils;

import java.util.ArrayList;

public class NodoPredicado {
	private int color;
	private int id;
	private int idPadre;
	private int idComponenteConexa;
	private boolean esNegado;
	private ArrayList<NodoPredicado> nextNodos;
	
	public NodoPredicado(int id, int idPadre, int c, boolean negado) {
		this.nextNodos = new ArrayList<NodoPredicado>();
		this.esNegado  = negado;
		this.idPadre   = idPadre;
		this.id    = id;
		this.color = c;
	}
	
	public void connect(NodoPredicado n) {
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
	
	public void setCC(int id) {
		this.idComponenteConexa = id;
	}
	
	public int getIdCC() {
		return this.idComponenteConexa;
	}
	
	public ArrayList<NodoPredicado> getAdyacentes() {
		return this.nextNodos;
	}
}
