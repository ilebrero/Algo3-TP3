package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//Grafo sobre Lista de Adyacencia

public class GrafoMaterias {
	protected ArrayList<Materia> materias;
	
	public GrafoMaterias() {
		materias = new ArrayList<Materia>();
	}
	
	public void addMateria(Materia m1){
		int id = materias.size();
		m1.setId(id);
		materias.add(m1);
		
	}
	
	public void connectMateria(int m1, int m2) {
		materias.get(m1).addMateria(materias.get(m2));
		materias.get(m2).addMateria(materias.get(m1));
	}
	
	public int size() {
		return materias.size();
	}
	
	public Materia getMateria(int i){
		return materias.get(i);
	}

	public List<Materia> getMaterias(){
		return materias;
	}
	
	private boolean esta(Materia m){
		for (Materia materia : materias) {
			if (m.getId() == materia.getId()){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * TODO: revizar el tema del grafo invertido
	 */
	/*
	public static Grafo grafoInvertido(Grafo g) {
		Grafo invertido = new Grafo();
		
		List<Materia> nodos = g.getMaterias();
		for (Materia m : nodos) {
			invertido.addMateria(m);
		}
		
		return invertido;
	}*/
}

