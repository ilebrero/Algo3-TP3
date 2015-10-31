package exercises3;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Grafo {
	private ArrayList<Materia> materias;
	public Grafo() {
		materias = new ArrayList<Materia>();
	}
	
	public void addMateria(Materia m1){
		materias.add(m1);	
	}
	
	public void connectMateria(int m1, int m2) {
		materias.get(m1).addMateria(materias.get(m2));
		materias.get(m2).addMateria(materias.get(m1));
	}
	
	public int size() {
		return materias.size();
	}
	public TreeSet<Materia> materias(){
		return new TreeSet<Materia>(materias) {
		};
	}
	
	private boolean esta(Materia m){
		for (Materia materia : materias) {
			if (m.getId() == materia.getId()){
				return true;
			}
		}
		return false;
	}
}

