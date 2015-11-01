package ejercicio1;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import utils.Grafo;
import utils.Materia;

public class Ejercicio1 {
	private Grafo grafo;
	
	public Ejercicio1() {
		grafo  = new Grafo();
	}
	
	private ArrayList< ArrayList<Materia> > kosaraju(Grafo grafo){
		ArrayList<Materia> orden = new ArrayList<Materia>();
		ArrayList<Materia> componente = new ArrayList<Materia>();
		ArrayList< ArrayList<Materia> > componentes = new ArrayList< ArrayList<Materia> >();
		boolean []usado = new boolean[grafo.size()];
		
		//recorre grafo por dfs, obtiene el numero de cada nodo
		for (int i = 0; i < usado.length; i++) {
			if (!usado[i]){
				dfs(grafo, usado, orden, i);
			}
		}
		
		//El grafo que usamos da igual invertido
		//revertir el grafo
		//Grafo grafoRevertido = reverseGraf(grafo);
		
		//recorrer grafo invertido por dfs, ordenado por nodos recorridos y no usado y devolver css's
		Collections.reverse(orden);
		
		for (Materia m : orden) {
			if (!usado[ m.getId() ]){
				dfs(grafo, usado, componente, m.getId());
				componentes.add(componente);
			}
		}
		
		return componentes;

	}
	
	/*
	 * esta implementacion de dfs es recursiva!!! 
	 * TODO: ver si se puede implementar iterativamente, puede quedar como experimentacion ver tiempos de corrida iterativo vs recursivo
	 */
	private void dfs(Grafo g, boolean[] usado, List<Materia> m, int i) {
		Materia actual = g.getMateria(i); 
		usado[ actual.getId() ] = true;
		
		for (Materia vecino : actual.getAdyacentes()) {
			if (!usado[ vecino.getId() ]) {
				dfs(g, usado, m, vecino.getId());
			}
		}
		
		m.add(actual);
	}
}
