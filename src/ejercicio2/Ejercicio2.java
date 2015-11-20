package ejercicio2;

import java.util.ArrayList;
import java.util.Collections;

import utils.Color;
import utils.GrafoPredicados;
import utils.NodoMateria;
import ejercicio1.Ejercicio1;

public class Ejercicio2 {
	private GrafoPredicados grafo;
	private Ejercicio1 ejercicio1;
	private ArrayList<Color> solucion;
	private int[] poda2;
	private int intentos = 0;
	private int poda1 = 0;
	
	public Ejercicio2(GrafoPredicados grafo) {
		this.grafo = grafo;
		poda2 = new int[grafo.size()];
		for (int i = 0; i < poda2.length; i++) {
			poda2[i] = -2;
		}
	}
	
	public int getIntentos() {
		return intentos;
	}

	public int getPoda1() {
		return poda1;
	}
	
	public ArrayList<Color> getSolucion() {
		return solucion;
	}
	
	public boolean solverWithBacktrack(){
		ArrayList<NodoMateria> nodos = new ArrayList<NodoMateria>();
		ejercicio1 = new Ejercicio1(grafo.size());
		
		for (NodoMateria nodoMateria : grafo.getMaterias()) {
			if (nodoMateria.getColoresPosibles().size() > 2){
				Collections.shuffle(nodoMateria.getColoresPosibles());
				nodos.add(nodoMateria);
			} else {
				for(int color : nodoMateria.getColoresPosibles()){
					nodoMateria.setColor(color);
				}
			}
		}
		
		if (nodos.isEmpty()){
			return (solucion = ejercicio1.solve(grafo)) != null;
		} else {
			return backTrack(nodos);			
		}
	}
	
	private boolean backTrack(ArrayList<NodoMateria> materiasColores){
		NodoMateria materia = materiasColores.get(0); // Materias con mas de 2 colores.
		materiasColores.remove(0);
		boolean tieneSolucion = false;
		int i = 0;
		
		if ( 1 != 1 && poda2(materia) != -1){
			if (materiasColores.size() == 0){
				if ((solucion = ejercicio1.solve(grafo)) != null){
					return true;
				}
			} else {
				if (backTrack(materiasColores)){
					return true;
				}
			}
			materia.clearColors();
		} else {
			while (i < materia.getColoresPosibles().size() && !tieneSolucion){
				
				materia.setColor(materia.getColoresPosibles().get(i)); // Seteo el color de backtrack
				if (poda1(materia,materia.getColoresPosibles().get(i))){
					if (materiasColores.size() == 0){
						intentos++;
						if ((solucion = ejercicio1.solve(grafo)) != null){
							return true;
						}
					} else {
						if (backTrack(materiasColores)){
							return true;
						}
					}
				} else {
					poda1++;
				}
				i++;
				materia.clearColors();
			}
		}
		materiasColores.add(0,materia);
		return false;
	}
	
	private boolean backTrack2Colors(ArrayList<NodoMateria> materiasColores){
		NodoMateria materia = materiasColores.get(0); // Materias con mas de 2 colores.
		materiasColores.remove(0);
		boolean tieneSolucion = false;
		int i = 0;
		
		if (poda2(materia) != -1){
			materia.setColor(materia.getColores().get(i)); // Seteo el color de backtrack
			if (materiasColores.size() == 0){
				if ((solucion = ejercicio1.solve(grafo)) != null){
					return true;
				}
			} else {
				if (backTrack2Colors(materiasColores)){
					return true;
				}
			}
			materia.clearColors();
		} else {
			while (i < materia.getColoresPosibles().size() && !tieneSolucion){
				
				materia.setColor(materia.getColoresPosibles().get(i)); // Seteo el color de backtrack
				if (i+1 < materia.getColoresPosibles().size()){
					materia.setColor(materia.getColoresPosibles().get(i+1));
				}
				if (poda1(materia,materia.getColoresPosibles().get(i))){
					if (materiasColores.size() == 0){
						intentos++;
						if ((solucion = ejercicio1.solve(grafo)) != null){
							return true;
						}
					} else {
						if (backTrack2Colors(materiasColores)){
							return true;
						}
					}
				} else {
					poda1++;
				}
			
				i += 2;
				
				materia.clearColors();
			}
		}
		materiasColores.add(0,materia);
		return false;
	}
	
	
	private boolean backTrackRandomizado(ArrayList<NodoMateria> materiasColores){
		NodoMateria materia = materiasColores.get(0); // Materias con mas de 2 colores.
		materiasColores.remove(0);
		boolean tieneSolucion = false;
		int i = 0;
		
		if (poda2(materia) != -1){
			System.out.println("encontre una excelente poda");
			if (materiasColores.size() == 0){
				if ((solucion = ejercicio1.solve(grafo)) != null){
					return true;
				}
			} else {
				if (backTrackRandomizado(materiasColores)){
					return true;
				}
			}
			materia.clearColors();
		} else {
			while (i < materia.getColoresPosibles().size() && !tieneSolucion){
				materia.setColor(materia.getColoresPosibles().get(i)); // Seteo el color de backtrack
				if (1 ==1 || poda1(materia,materia.getColoresPosibles().get(i))){
					if (materiasColores.size() == 0){
						intentos++;
						if ((solucion = ejercicio1.solve(grafo)) != null){
							return true;
						}
					} else {
						if (backTrackRandomizado(materiasColores)){
							return true;
						}
					}
				} else {
					poda1++;
				}
				i++;
				materia.clearColors();
			}
		}
		materiasColores.add(0,materia);
		return false;
	}
	
	private boolean poda1(NodoMateria materia, int color){
		for (NodoMateria materiaVecina : materia.getAdyacentes()) {
			if (materiaVecina.getColores().size() == 1 && materiaVecina.getColores().contains(color)){
				return false;
			}
		}
		
		return true;
	}
	
	private Integer poda2(NodoMateria materia){
		if (poda2[materia.getId()] != -2){
			if (poda2[materia.getId()] == -1){
				return -1;
			}
			materia.setColor(poda2[materia.getId()]);
			return poda2[materia.getId()];
		} else{
			ArrayList<Integer> coloresPosibles = new ArrayList<Integer>(materia.getColoresPosibles());
			ArrayList<Integer> coloresVecinos  = new ArrayList<Integer>();
		
			for (NodoMateria materiaVecina : materia.getAdyacentes()) {
				coloresVecinos.addAll(materiaVecina.getColores());
			}
			
			coloresPosibles.removeAll(coloresVecinos);
			if (coloresPosibles.size() == 0){
				poda2[materia.getId()] = -1;
				return -1;
			} else {
				poda2[materia.getId()] = coloresPosibles.get(0);
				materia.setColor(coloresPosibles.get(0));
				return coloresPosibles.get(0);
			}
			}
	}
	
	
}
// Complejidad en peor caso es un completo de ((mayorCantColores) ^ (CantidadDeMateria)) * O(costo Ej 1) . 
//cantidad de materias asumimos en peor caso que todas tienen mas de 2 colores.


