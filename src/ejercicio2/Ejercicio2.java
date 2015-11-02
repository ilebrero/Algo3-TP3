package ejercicio2;

import java.util.ArrayList;
import java.util.Collection;

import ejercicio1.Ejercicio1;
import utils.GrafoMaterias;
import utils.Materia;

public class Ejercicio2 {
	private GrafoMaterias grafo;
	private Ejercicio1 ejercicio1;
	public Ejercicio2() {
		grafo = new GrafoMaterias();
		
	}
	
	private boolean backTrack(ArrayList<Materia> materiasColores){
		Materia materia = materiasColores.get(0); // Materias con mas de 2 colores.
		materiasColores.remove(0);
		boolean solucion = false; int i = 0;
		if (poda2(materia) != -1){
			materia.setColor(materia.getColores().get(i)); // Seteo el color de backtrack
			if (backTrack(materiasColores)){
				return true;
			}
		} else {
			while (i < materia.getColores().size() || ! solucion){
				materia.setColor(materia.getColores().get(i)); // Seteo el color de backtrack
				if (poda1(materia,materia.getColores().get(i))){
					if (materiasColores.size() == 0){
						if (ejercicio1.solve()){
							return true;
						}
					} else {
						if (backTrack(materiasColores)){
							return true;
						}
					}
				}
			}
		}
		return ejercicio1.solve();
	}
	private boolean poda1(Materia materia, int color){
		for (Materia materiaVecina : materia.getAdyacentes()) {
			if (materiaVecina.getColores().size() == 1 && materiaVecina.getColores().contains(color)){
				return false;
			}
		}
		return true;
	}
	private Integer poda2(Materia materia){
		ArrayList<Integer> coloresPosibles = new ArrayList<Integer>(materia.getColores());
		ArrayList<Integer> coloresVecinos = new ArrayList<Integer>();
		for (Materia materiaVecina : materia.getAdyacentes()) {
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


