package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import utils.GrafoEstados;
import utils.NodoEstado;

public class Ejercicio1 {
	public static ArrayList< Componente > kosaraju(GrafoEstados grafo){
		boolean [] usado = new boolean[grafo.size()];
		ArrayList<NodoEstado> orden	= new ArrayList<NodoEstado>();
		ArrayList< Componente > componentes = new ArrayList< Componente >();
		
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
				ArrayList<NodoEstado> componenteActual = new ArrayList<NodoEstado>(); 
				dfs(grafoInvertido, usado, componenteActual, m.getId());
				for (NodoEstado n : componenteActual) n.setCC( componentes.size() );
				componentes.add(new Componente(componenteActual, componentes.size()));
			}
		}
		
		return componentes;
	}
	
	/*/
	 * TODO: ver si se puede implementar iterativamente, puede quedar como experimentacion ver tiempos de corrida iterativo vs recursivo
	/*/
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
	
	private static boolean tieneSolucion(ArrayList< Componente > componentes, ArrayList< ArrayList<Integer> > coloreos){
		for (Componente c : componentes) {
			coloreos.add(new ArrayList<Integer>());
			c.valordeVerdad( coloreos.get( coloreos.size() - 1 ) );	
		}
		
		armarGrafoDeComponentesConexas(componentes);
		
		return checkThruthValues(componentes);
	}

	private static boolean checkThruthValues(ArrayList<Componente> componentes) {
		boolean result = true;
		
		if (componentes.size() == 1) result = !componentes.get(0).valorDeVerdad; 
			
		for (Componente c : componentes) {
			System.out.println(c.valorDeVerdad);
			for (Componente vecino : c.getVecinos()) {
				System.out.println(c.id + " con valor: " + c.valorDeVerdad + " contra " + vecino.id + " con valor: " + vecino.valorDeVerdad );
				if (c.valorDeVerdad && !vecino.valorDeVerdad) result = false;
			}
		}
		
		return result;
	}
	
	private static void armarGrafoDeComponentesConexas(ArrayList<Componente> componentes) {
		for (Componente c : componentes) {
			for (NodoEstado actual : c.nodos) {
				for (NodoEstado adyacente : actual.getAdyacentes()) {
					if (adyacente.getIdCC() != actual.getIdCC()) {
						c.addVecino(componentes.get(adyacente.getIdCC()));
					}
				}
			}
		}
	}

	public static ArrayList<ArrayList<Integer>> solve(GrafoEstados grafo) {
		grafo.generarGrafoDeEstados();
		ArrayList< Componente > cc = kosaraju(grafo);
		ArrayList< ArrayList<Integer> > solucion = new ArrayList<ArrayList<Integer>>();

		if (tieneSolucion(cc, solucion)){
			return solucion;
		} else {
			return null;
		}
	}
}
