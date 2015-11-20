package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utils.Color;
import utils.Componente;
import utils.GrafoPredicados;
import utils.NodoPredicado;

public class Ejercicio1 {
	int cantMaterias;
	
	public Ejercicio1(int materias) {
		cantMaterias = materias;
	}
	
	public ArrayList< Componente > kosaraju(GrafoPredicados grafo){
		boolean [] usado = new boolean[grafo.sizeEstados()];
		ArrayList<NodoPredicado> orden	= new ArrayList<NodoPredicado>();
		ArrayList< Componente > componentes = new ArrayList< Componente >();
		
		for (int i = 0; i < usado.length; i++) {
			if (!usado[i]){
				dfs(grafo.getNodosEstado(), usado, orden, i);
			}
		}
		
		ArrayList<NodoPredicado> grafoInvertido = grafo.grafoInvertido();
		Collections.reverse(orden);
		Arrays.fill(usado, false);
		
		for (NodoPredicado m : orden) {
			if (!usado[ m.getId() ]){
				ArrayList<NodoPredicado> componenteActual = new ArrayList<NodoPredicado>();
				
				dfs(grafoInvertido, usado, componenteActual, m.getId());
				for (NodoPredicado n : componenteActual) {
					n.setCC( componentes.size() );
				}
				componentes.add(new Componente(componenteActual, componentes.size()));
			}
		}
		
		return componentes;
	}
	
	private void dfs(ArrayList<NodoPredicado> g, boolean[] usado, List<NodoPredicado> m, int i) {
		NodoPredicado actual = g.get(i); 
		usado[ actual.getId() ] = true;
		
		for (NodoPredicado vecino : actual.getAdyacentes()) {
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
		
		return checkThruthValues(componentes);
	}

	private boolean checkThruthValues(ArrayList< Componente > componentes) {
		boolean result = true;
		
		for (Componente c : componentes) {
			result = result && !c.valorDeVerdad;
		}
		
		return result;
	}
	
	private Color[] armarColoreo(ArrayList<Componente> componentes) {
		Color [] solucion = new Color[cantMaterias];
		int usados[] = new int[cantMaterias];
		boolean pintadaActual;
		int ocupados = 0;
		
		while (ocupados < cantMaterias) {
			for (Componente c : componentes) {

				if (c.hasPadre()) {
					if (c.getValorVerdadPadre()) {
						pintadaActual = true;
					} else {
						pintadaActual = false;
					}
				} else {
					pintadaActual = false;
				}
				
				//checkea por contradicciones
				for (NodoPredicado n : c.nodos) {
					if (usados[n.getPadreId()] == -1) {
						if (n.getColor() == solucion[n.getPadreId()].getColor() && n.getNegado()) {
							if (pintadaActual = true) { 
								return null;
							} else {
								pintadaActual = true;
								c.setValorDeVerdad(true);
							}
						}
					} else {
						if (usados[n.getPadreId()] == 1) {
							if (n.getColor() == solucion[n.getPadreId()].getColor() && !n.getNegado()) {
								if (pintadaActual = true) { 
									return null;
								} else {
									pintadaActual = true;
									c.setValorDeVerdad(true);
								}
							}
						}
					}
				}
				
				for (NodoPredicado n : c.nodos) {
					if (!n.getNegado() == pintadaActual) {
						if (usados[n.getPadreId()] == 0) {
							Color colorActual = new Color(n.getColor(), n.getPadreId());
							
							solucion[n.getPadreId()] = colorActual;
							
							if (n.getNegado()) {
								usados[n.getPadreId()] = -1;
							} else {
								usados[n.getPadreId()] = 1;
							}
							
							ocupados++;
						}
					}
				}
			}
		}
		
		return solucion;
	}
	
	private void armarGrafoDeComponentesConexas(ArrayList<Componente> componentes) {
		for (Componente c : componentes) {
			for (NodoPredicado actual : c.nodos) {
				for (NodoPredicado adyacente : actual.getAdyacentes()) {
					if (adyacente.getIdCC() != actual.getIdCC()) {
						Componente vecino = componentes.get(adyacente.getIdCC()); 
						c.setPadre(vecino);
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
			Color [] sol = armarColoreo(componentesConexas);
			
			if (sol != null) {
				return new ArrayList<Color>(Arrays.asList(sol));				
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
