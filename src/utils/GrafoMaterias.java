package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Grafo sobre Lista de Adyacencia

public class GrafoMaterias implements java.io.Serializable {
	protected ArrayList<NodoMateria> grafoMateria;
	
	public GrafoMaterias() {
		grafoMateria = new ArrayList<NodoMateria>();
	}
	
	public void addMateria(NodoMateria m) {
		int id = grafoMateria.size();
		m.setId(id);
		
		grafoMateria.add(m);
	}
	
	public void connectMateria(int m1, int m2) {
		grafoMateria.get(m1).addAdyacente(grafoMateria.get(m2));
		grafoMateria.get(m2).addAdyacente(grafoMateria.get(m1));
	}
	public void connectMateria(int i, int j, int k, int l) {
		connectMateria(i, j);
		grafoMateria.get(i).setColor(l);
		grafoMateria.get(j).setColor(k);
		
	}
	
	public int size() {
		return grafoMateria.size();
	}
	
	public NodoMateria getMateria(int i){
		return grafoMateria.get(i);
	}

	public List<NodoMateria> getMaterias(){
		return grafoMateria;
	}
	
	protected boolean esta(NodoMateria m){
		for (NodoMateria materia : grafoMateria) {
			if (m.getId() == materia.getId()){
				return true;
			}
		}
		return false;
	}
	public int findConflicts(int[] colores){
		LinkedList<NodoMateria> cola = new LinkedList<NodoMateria>();
		cola.add(grafoMateria.get(0));
		Boolean[] visitado = new Boolean[grafoMateria.size()];
		visitado[0] = true;
		for(int i = 1; i < visitado.length ; i++){
			visitado[i] = false;
		}
		int contradicciones = 0; NodoMateria actual;
		while(! cola.isEmpty()){
			
			actual = cola.pop();
			List<NodoMateria> vecinos = actual.getAdyacentes();		
			for (int k = 0; k < vecinos.size() ; k++) {
				NodoMateria vecinoActual = vecinos.get(k);
				
				if (! visitado[vecinoActual.getId()]){
					visitado[vecinoActual.getId()] = true;			
					cola.push(vecinoActual);
				}
				
				if (colores[actual.getId()] == colores[vecinoActual.getId()]){
				//	System.out.println("Hubo conflicto " + actual.getId() + "con" + vecinoActual.getId() +"con el color: "+ colores[actual.getId()]);
					contradicciones++;
				}
			}	
		}
		return contradicciones/2;	
	}

	
}

