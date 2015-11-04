package ejercicio2;

import java.util.ArrayList;
import ejercicio1.Ejercicio1;
import utils.GrafoEstados;
import utils.GrafoMaterias;
import utils.NodoMateria;

public class Ejercicio2 {
	private GrafoEstados grafo;
	private Ejercicio1 ejercicio1;
	private ArrayList<ArrayList<Integer>> solucion;
	public Ejercicio2() {
		grafo = new GrafoEstados();
	}
	
	public ArrayList<ArrayList<Integer>> getSolucion() {
		return solucion;
	}
	
	public boolean solverWithBacktrack(){
		ArrayList<NodoMateria> nodos = new ArrayList<NodoMateria>();
		for (NodoMateria nodoMateria : grafo.getMaterias()) {
			if (nodoMateria.getColores().size() > 2){
				nodos.add(nodoMateria);
			}
		}
		if (nodos.isEmpty()){
			return (solucion = Ejercicio1.solve(grafo)) != null;
		} else {
			return backTrack(nodos);			
		}
	}
	
	private boolean backTrack(ArrayList<NodoMateria> materiasColores){
		NodoMateria materia = materiasColores.get(0); // Materias con mas de 2 colores.
		materiasColores.remove(0);
		boolean tieneSolucion = false;
		int i = 0;
		
		if (poda2(materia) != -1){
			materia.setColor(materia.getColores().get(i)); // Seteo el color de backtrack
			if (materiasColores.size() == 0){
				if ((solucion = Ejercicio1.solve(grafo)) != null){
					return true;
				}
			} else {
				if (backTrack(materiasColores)){
					return true;
				}
			}
			materia.clearColors();
		} else {
			while (i < materia.getColores().size() && !tieneSolucion){
				materia.setColor(materia.getColores().get(i)); // Seteo el color de backtrack
				if (poda1(materia,materia.getColores().get(i))){
					if (materiasColores.size() == 0){
						if ((solucion = Ejercicio1.solve(grafo)) != null){
							return true;
						}
					} else {
						if (backTrack(materiasColores)){
							return true;
						}
					}
				}
				materia.clearColors();
			}
		}
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
		ArrayList<Integer> coloresPosibles = new ArrayList<Integer>(materia.getColores());
		ArrayList<Integer> coloresVecinos  = new ArrayList<Integer>();
	
		for (NodoMateria materiaVecina : materia.getAdyacentes()) {
			coloresVecinos.addAll(materiaVecina.getColores());
		}
		
		coloresPosibles.retainAll(coloresVecinos);
		if (coloresPosibles.size() == materia.getColores().size()){
			return -1;
		} else {
			for (int i = 0; i < materia.getColores().size(); i++) {
				if (!coloresPosibles.contains(materia.getColores().get(i))){
					return materia.getColores().get(i);
				}
			}
		}
		
		return -1;
	}
	
	
	
}
// Complejidad en peor caso es un completo de ((mayorCantColores) ^ (CantidadDeMateria)) * O(costo Ej 1) . 
//cantidad de materias asumimos en peor caso que todas tienen mas de 2 colores.


