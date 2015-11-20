package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Componente {
		
	public ArrayList<NodoPredicado> nodos;
	Componente padre;
	public boolean valorDeVerdad;
	int id;
	
	public Componente(ArrayList<NodoPredicado> nodos, int id){
		this.nodos = nodos;
		this.padre = null;
		this.id = id;
	}
	
	private boolean calcularValordeVerdad() {
		boolean result = false;
		int[] idPadresUsados = new int [maxPadreId() + 1];
		Arrays.fill(idPadresUsados, Integer.MAX_VALUE);
		
		for (NodoPredicado n : nodos) {
			if (idPadresUsados[n.getPadreId()] != Integer.MAX_VALUE) {
				int colorYaUsado = idPadresUsados[n.getPadreId()];
				int colorActual;
				
				if (n.getNegado()) {
					colorActual = -n.getColor();
				} else {
					colorActual = n.getColor();
				}
				
				if (colorYaUsado == -colorActual) {
					result = true;
				}
			} else {
				if (n.getNegado()) {
					idPadresUsados[n.getPadreId()] = -n.getColor();
				} else {
					idPadresUsados[n.getPadreId()] = n.getColor();
				}
			}
		}
		return result;
	}

	public int maxPadreId() {
		int max = 0;
		for (NodoPredicado n : nodos) {
			if (n.getPadreId() > max) max = n.getPadreId(); 
		}
		
		return max;
	}
	
	public void valordeVerdad() {
		this.valorDeVerdad = calcularValordeVerdad();
	}
	
	public void setPadre(Componente c) {
		this.padre = c;
	}
	
	public boolean getValorVerdadPadre() {
		return this.padre.valorDeVerdad;
	}
	
	public boolean hasPadre() {
		return this.padre != null;
	}
	
	public void setValorDeVerdad(boolean v) {
		this.valorDeVerdad = v;
	}
	
	public Componente getPadre() {
		return this.padre;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<NodoPredicado> getNodos() {
		return this.nodos;
	}
}
