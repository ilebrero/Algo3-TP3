package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import utils.GrafoEstados;
import utils.GrafoMaterias;
import utils.NodoEstado;

public class Ejercicio1 {
	private GrafoMaterias grafo;
	
	public Ejercicio1() {
		grafo  = new GrafoMaterias();
	}


	//TODO: pasar a privado	
	public static ArrayList< ArrayList<NodoEstado> > kosaraju(GrafoEstados grafo){
		boolean [] usado = new boolean[grafo.size()];
		ArrayList<NodoEstado> orden		 = new ArrayList<NodoEstado>();
		ArrayList<NodoEstado> componente = new ArrayList<NodoEstado>();
		ArrayList< ArrayList<NodoEstado> > componentes = new ArrayList< ArrayList<NodoEstado> >();
		
		//obtiene orden para recorrer
		for (int i = 0; i < usado.length; i++) {
			if (!usado[i]){
				dfs(grafo.getNodosEstado(), usado, orden, i);
			}
		}
		
		ArrayList<NodoEstado> grafoInvertido = grafo.grafoInvertido();
		Collections.reverse(orden);
		Arrays.fill(usado, false);
		
		//encuentra las componentes por separado
		for (NodoEstado m : orden) {
			if (!usado[ m.getId() ]){
				dfs(grafoInvertido, usado, componente, m.getId());
				componentes.add(componente);
				componente = new ArrayList<NodoEstado>();
			}
		}
		
		return componentes;
	}
	
	/*
	 * TODO: ver si se puede implementar iterativamente, puede quedar como experimentacion ver tiempos de corrida iterativo vs recursivo
	 */
	private static void dfs(ArrayList<NodoEstado> g, boolean[] usado, List<NodoEstado> m, int i) {
		NodoEstado actual = g.get(i); 
		usado[ actual.getId() ] = true;
		
		for (NodoEstado vecino : actual.getAdyacentes()) {
			if (!usado[ vecino.getId() ]) {
				dfs(g, usado, m, vecino.getId());
			}
		}
		
		m.add(actual);
	}

	private boolean hasSolution(ArrayList< ArrayList<NodoEstado> > componentes){
		boolean result = true;

		for (ArrayList<NodoEstado> componente : componentes) {
			result = result && truthValue(componente);
		}

		return result;
	}

	private boolean truthValue(ArrayList<NodoEstado> componente) {
		boolean result  = true;
		HashMap<Integer, Boolean> usados = new HashMap<Integer, Boolean>();
		for (NodoEstado n : componente) usados.put(n.getId(), false);
		
		int primerColor = componente.get(0).getColor();
		int primerId    = componente.get(0).getPadreId();
		usados.put(componente.get(0).getId(), true);
		
		Stack<NodoEstado> proximos = new Stack<NodoEstado>();
		proximos.push(componente.get(0));
		
		while (proximos.size() > 0 && result) {
			NodoEstado actual = proximos.pop();
			
			for (NodoEstado n : actual.getAdyacentes()) {
				if (usados.containsKey(n.getId()) && !usados.get(n.getId())) {
					usados.put(n.getId(), true);
					proximos.push(n);
					
					if (n.getPadreId() == primerId && n.getColor() == primerColor && n.getNegado()) result = false;
				}
			}
		}
		
		return result;
	}

	public boolean solve() {
		// TODO Auto-generated method stub
		return false;
	}
}
