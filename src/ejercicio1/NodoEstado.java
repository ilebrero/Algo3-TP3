package ejercicio1;

import java.util.ArrayList;

public class NodoEstado {
	private int color;
	private ArrayList<NodoEstado> nextNodos;
	private boolean esNegado = false;
	
	public NodoEstado() {
		nextNodos = new ArrayList<NodoEstado>();
	}
	
	public void setNegado(boolean negado) {
		this.esNegado = negado;
	}
	public void setColor(int c) {
		this.color = c;
	}

	public void connect(NodoEstado n) {
		nextNodos.add(n);
	}
}
