package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utils.GrafoEstados;
import utils.GrafoMaterias;
import utils.NodoEstado;

public class Ejercicio1 {
	private GrafoMaterias grafo;
	
	public Ejercicio1() {
		grafo  = new GrafoMaterias();
	}
	
	public static ArrayList< ArrayList<NodoEstado> > kosaraju(GrafoEstados grafo){
		boolean [] usado = new boolean[grafo.size()];
		ArrayList<NodoEstado> orden		 = new ArrayList<NodoEstado>();
		ArrayList<NodoEstado> componente = new ArrayList<NodoEstado>();
		ArrayList< ArrayList<NodoEstado> > componentes = new ArrayList< ArrayList<NodoEstado> >();
		
		//recorre grafo por dfs, obtiene el numero de cada nodo
		for (int i = 0; i < usado.length; i++) {
			if (!usado[i]){
				dfs(grafo.getNodosEstado(), usado, orden, i);
			}
		}
		
		//revertir el grafo
		ArrayList<NodoEstado> grafoInvertido = grafo.grafoInvertido();
		
		Collections.reverse(orden);
		Arrays.fill(usado, false);
		
		//recorrer grafo invertido por dfs, ordenado por nodos recorridos y no usado y devolver css's
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
	 * esta implementacion de dfs es recursiva!!! 
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

	public boolean solve() {
		// TODO Auto-generated method stub
		return false;
	}
}
