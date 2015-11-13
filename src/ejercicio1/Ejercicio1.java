package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utils.Color;
import utils.Componente;
import utils.GrafoPredicados;
import utils.NodoEstado;

public class Ejercicio1 {
	int cantMaterias;
	
	public Ejercicio1(int materias) {
		cantMaterias = materias;
	}
	
	public ArrayList< Componente > kosaraju(GrafoPredicados grafo){
		boolean [] usado = new boolean[grafo.sizeEstados()];
		ArrayList<NodoEstado> orden	= new ArrayList<NodoEstado>();
		ArrayList< Componente > componentes = new ArrayList< Componente >();
		
		for (int i = 0; i < usado.length; i++) {
			if (!usado[i]){
				dfs(grafo.getNodosEstado(), usado, orden, i);
			}
		}
		
		ArrayList<NodoEstado> grafoInvertido = grafo.grafoInvertido();
		Collections.reverse(orden);
		Arrays.fill(usado, false);
		
		for (NodoEstado m : orden) {
			if (!usado[ m.getId() ]){
				ArrayList<NodoEstado> componenteActual = new ArrayList<NodoEstado>();
				
				dfs(grafoInvertido, usado, componenteActual, m.getId());
				for (NodoEstado n : componenteActual) {
					n.setCC( componentes.size() );
				}
				componentes.add(new Componente(componenteActual, componentes.size()));
			}
		}
		
		return componentes;
	}
	
	private void dfs(ArrayList<NodoEstado> g, boolean[] usado, List<NodoEstado> m, int i) {
		NodoEstado actual = g.get(i); 
		usado[ actual.getId() ] = true;
		
		for (NodoEstado vecino : actual.getAdyacentes()) {
			if (!usado[ vecino.getId() ]) {
				dfs(g, usado, m, vecino.getId());
			}
		}
		
		m.add(actual);
	}
	
	private boolean tieneSolucion(ArrayList< Componente > componentes){
		for (Componente c : componentes) {
			c.valordeVerdad();	
		}
		
		return checkThrtuthValues(componentes);
	}

	private boolean checkThrtuthValues(ArrayList< Componente > componentes) {
		boolean result = true;
		
		for (Componente c : componentes) {
			result = result && !c.valorDeVerdad;
		}
		
		return result;
	}
	
	private ArrayList<Color> armarColoreo(ArrayList<Componente> componentes) {
		ArrayList<Color> solucion = new ArrayList<Color>();
		boolean usados[] = new boolean[cantMaterias];
		int ocupados = 0;
		
		while (ocupados < cantMaterias) {
			for (Componente c : componentes) {
				for (NodoEstado n : c.nodos) {
					if (n.getNegado() && !usados[n.getPadreId()]) {
						Color colorActual = new Color(n.getColor(), n.getPadreId());
						
						solucion.add(colorActual);
						usados[n.getPadreId()] = true;
						ocupados++;
					}
				}
			}
		}
		
		return solucion;
	}
	
	private void armarGrafoDeComponentesConexas(ArrayList<Componente> componentes) {
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

	public ArrayList<Color> solve(GrafoPredicados grafo) {
		grafo.generarGrafoDeEstados();
		ArrayList< Componente > componentesConexas = kosaraju(grafo);
		
		if (tieneSolucion(componentesConexas)){
			armarGrafoDeComponentesConexas(componentesConexas);
			ArrayList<Color> sol =armarColoreo(componentesConexas);
			
			return sol;
		} else {
			return null;
		}
	}
}
