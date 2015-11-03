package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//Grafo sobre Lista de Adyacencia

public class GrafoMaterias {
	protected ArrayList<Materia> grafoMateria;
	
	public GrafoMaterias() {
		grafoMateria = new ArrayList<Materia>();
	}
	
	public void addMateria(Materia m) {
		int id = grafoMateria.size();
		m.setId(id);
		
		grafoMateria.add(m);
	}
	
	public void connectMateria(int m1, int m2) {
		grafoMateria.get(m1).addAdyacente(grafoMateria.get(m2));
		grafoMateria.get(m2).addAdyacente(grafoMateria.get(m1));
	}
	
	public int size() {
		return grafoMateria.size();
	}
	
	public Materia getMateria(int i){
		return grafoMateria.get(i);
	}

	public List<Materia> getMaterias(){
		return grafoMateria;
	}
	
	private boolean esta(Materia m){
		for (Materia materia : grafoMateria) {
			if (m.getId() == materia.getId()){
				return true;
			}
		}
		return false;
	}
}

