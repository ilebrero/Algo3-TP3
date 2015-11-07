package ejercicio2;

import java.util.ArrayList;

import utils.GrafoEstados;
import utils.NodoMateria;
import ejercicio1.Ejercicio1;

public class Ejercicio2 {
	private GrafoEstados grafo;
	private Ejercicio1 ejercicio1;
	private ArrayList<Integer> solucion;
	public Ejercicio2(GrafoEstados grafo) {
		this.grafo = grafo;
	}
	
	public ArrayList<Integer> getSolucion() {
		return solucion;
	}
	
	public boolean solverWithBacktrack(){
		ArrayList<NodoMateria> nodos = new ArrayList<NodoMateria>();
		ejercicio1 = new Ejercicio1(grafo.size());
		
		for (NodoMateria nodoMateria : grafo.getMaterias()) {
			if (nodoMateria.getColoresPosibles().size() > 2){
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
		
		if (poda2(materia) != -1){
			System.out.println("encontre una excelente poda");
			materia.setColor(materia.getColores().get(i)); // Seteo el color de backtrack
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
//				if (materia.getId() == 0){
					System.out.println("Pruebo con el color " + materia.getColoresPosibles().get(i) + "de la materia" + materia.getId());
					System.out.println("tiene " + materia.getColoresPosibles().size() + "," + materia.getColores().size());
//				}
//				if (poda1(materia,materia.getColoresPosibles().get(i))){
					if (materiasColores.size() == 0){
//						System.out.println("pruebo solucion");
						System.out.println("Intentando Pruebo con el color " + grafo.getMateria(0).getColor(0) + "de la materia" + grafo.getMateria(0).getId());
						if ((solucion = ejercicio1.solve(grafo)) != null){
							System.out.println("Encontre solucion");
							return true;
						}
					} else {
						if (backTrack(materiasColores)){
							return true;
						}
					}
//				}
				i++;
				materia.clearColors();
			}
		}
		materiasColores.add(materia);
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


