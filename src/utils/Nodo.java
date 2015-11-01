package utils;

import java.util.ArrayList;

public class Nodo {
	private int color;
	private ArrayList<Nodo> nextNodos;
	private boolean esNegado = false;
	
	public Nodo() {
		nextNodos = new ArrayList<Nodo>();
	}
	
	public void setNegado(boolean negado) {
		this.esNegado = negado;
	}
	public void setColor(int c) {
		this.color = c;
	}

	public void connect(Nodo n) {
		nextNodos.add(n);
	}
}
