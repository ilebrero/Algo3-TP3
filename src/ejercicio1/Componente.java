package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import utils.NodoEstado;

public class Componente {
		
	ArrayList<NodoEstado> nodos;
	ArrayList<Componente> vecinos;
	int id;
	boolean valorDeVerdad;
	
	public Componente(ArrayList<NodoEstado> nodos, int id){
		this.nodos = nodos;
		this.vecinos = new ArrayList<Componente>();
		this.id = id;
	}
	
	private boolean calcularValordeVerdad(ArrayList<Integer> coloreo) {
		boolean result = false;
		int[] idPadresUsados = new int [maxPadreId() + 1];
		Arrays.fill(idPadresUsados, Integer.MAX_VALUE);
		
		for (NodoEstado n : nodos) {
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

	private int maxPadreId() {
		int max = 0;
		for (NodoEstado n : nodos) {
			if (n.getPadreId() > max) max = n.getPadreId(); 
		}
		
		return max;
	}
	
	public void valordeVerdad(ArrayList<Integer> coloreo) {
		this.valorDeVerdad = calcularValordeVerdad(coloreo);
	}
	
	public void addVecino(Componente v) {
		this.vecinos.add(v);
	}
	
	public ArrayList<Componente> getVecinos() {
		return this.vecinos;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<NodoEstado> getNodos() {
		return this.nodos;
	}
}
