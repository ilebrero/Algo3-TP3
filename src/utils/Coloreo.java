package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coloreo {
	
	public class Conflicto {
		int id;
		ArrayList<NodoMateria> conflicto;
		
		public Conflicto(int id, ArrayList<NodoMateria> conflictos) {
			this.id = id;
			this.conflicto = conflictos; 
		}
		
		public ArrayList<NodoMateria> getConflictos() {
			return conflicto;
		}
		
		public int getId() {
			return id;
		}
	}
	
	private List<NodoMateria> grafo;
	private List<Color> colores;
	private List<Conflicto> conflictos;
	private int cantidadConflictos;
	private boolean esValido;

	public Coloreo(GrafoPredicados grafo, ArrayList<Color> colores) {
		conflictos = new ArrayList<Conflicto>();

		this.cantidadConflictos = 0;
		this.colores = colores;
		this.grafo   = grafo.getMaterias();
		
		Collections.sort(this.colores);
		
		calcularEsValido();
	}
	
	private void calcularEsValido() {
		this.esValido = true;
		 
		for (NodoMateria n : grafo) {
			ArrayList<NodoMateria> conflictosActuales = new ArrayList<NodoMateria>();

			for (NodoMateria vecino : n.getAdyacentes()) {
				int colorNodo1, colorNodo2;
				
				colorNodo1 = colores.get(n.getId()).getColor();
				colorNodo2 = colores.get(vecino.getId()).getColor();
				
				if (colorNodo1 == colorNodo2) {
					cantidadConflictos++;
					conflictosActuales.add(vecino);
				}
			}
			
			if (!conflictosActuales.isEmpty()) {
				System.out.println("agrego a " + n.getId());
				Conflicto actual = new Conflicto(n.getId(), conflictosActuales);
				conflictos.add(actual);
				esValido = false;
			}
		}
	}
	
	public boolean esValido() {
		return this.esValido;
	}
	
	public int cantidadDeConflictos() {
		return this.cantidadConflictos/2;
	}
	
	public List<Color> getColores() {
		return this.colores;
	}
	
	public List<Conflicto> getConflictos() {
		return this.conflictos;
	}
}
