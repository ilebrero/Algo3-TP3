package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
				System.out.println("size: " + componentes.size());
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
			ArrayList<Integer> coloreo = new ArrayList<Integer>();
			
			coloreos.add(new ArrayList<Integer>());
			c.valordeVerdad( coloreos.get( coloreos.size() - 1 ) );	
		}
		
		armarGrafoDeComponentesConexas(componentes);
		
		System.out.println("valores:");
		for (Componente c : componentes) {
			System.out.println(c.valorDeVerdad);
		}
		
		return dfs2(componentes);
	}

	/*private static boolean df2recu() {
		
	}*/
	
	private static boolean dfs2(ArrayList<Componente> componentes) {
		Stack<Componente> pila = new Stack<Componente>();
		boolean usados[] = new boolean[ componentes.size() ];
		boolean result = true;
		
		Arrays.fill(usados, false);
		
		pila.push(componentes.get(0));
		result = componentes.get(0).valorDeVerdad;
		
		if (componentes.size() == 1) result = componentes.get(0).valorDeVerdad;
		
		while (!pila.isEmpty()) {
			Componente actual = pila.pop();
			usados[ actual.getId() ] = true;
			boolean valorActual = result;
			for (Componente c : actual.vecinos) {
				System.out.println("");
				if (!usados[ c.getId() ]) {					
					System.out.println(actual.valorDeVerdad+ " con " + c.valorDeVerdad);
					if (actual.valorDeVerdad && !c.valorDeVerdad) result = false;
					pila.push(c);
				}
			}
		}
		
		return !result;
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
