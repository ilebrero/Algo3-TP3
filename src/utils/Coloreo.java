package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coloreo {
	
	public class Conflicto {
		int id;
		ArrayList<NodoMateria> conflicto;
	}
	
	private List<NodoMateria> grafo;
	private List<Color> colores;
	private List<Conflicto> conflictos;
	private boolean esValido;

	public Coloreo(GrafoPredicados grafo, ArrayList<Color> colores) {
		conflictos = new ArrayList<Conflicto>();

		this.colores = colores;
		this.grafo   = grafo.getMaterias();
		
		Collections.sort(colores);
		
		esValido();
	}
		
	private void esValido() {
		this.esValido = true;
		 
		for (Color c : colores) {
			System.out.println(c.getId());
			
			int idActual = c.getId();
			NodoMateria actual = grafo.get(idActual);
			
			
		}
	}
}
