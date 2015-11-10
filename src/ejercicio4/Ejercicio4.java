package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import ejercicio3.Ejercicio3;
import utils.Color;
import utils.Coloreo;
import utils.Coloreo.Conflicto;
import utils.GrafoPredicados;
import utils.NodoMateria;

public class Ejercicio4 {
	private GrafoPredicados grafo;
	private Ejercicio3 ej3;
	private Coloreo coloreoActual;
	
	public Ejercicio4(GrafoPredicados grafo) {
		this.grafo = grafo;
		ej3 = new Ejercicio3(grafo);
	}
	
	public Coloreo solve() {
		ArrayList<Color> colores = new ArrayList<Color>(); 
		ej3.solve();
		int [] vectorColores = ej3.getColoreo();
		
		for (int i = 0; i < vectorColores.length; ++i) {
			Color actual = new Color(vectorColores[i], i);
			colores.add(actual);
		}
		
		coloreoActual = new Coloreo(grafo, colores);
		
		if (coloreoActual.esValido()) {
			return coloreoActual;
		} else {
			return resolve(coloreoActual);
		}
	}

	private Coloreo resolve(Coloreo colores) {
		int noCambio = 0;
		Coloreo nuevoColoreo;
		Coloreo mejorColoreo = colores;
		
		System.out.println("antes");
		while (!mejorColoreo.esValido() && noCambio < 5) {
			System.out.println("despues");
			nuevoColoreo = getProximoColoreo(mejorColoreo);
			
			//if (nuevoColoreo.equals(mejorColoreo)) return mejorColoreo;
			
			if (nuevoColoreo.cantidadDeConflictos() <= mejorColoreo.cantidadDeConflictos()) {
				mejorColoreo = nuevoColoreo;
				
				if (nuevoColoreo.cantidadDeConflictos() == mejorColoreo.cantidadDeConflictos()) {
					noCambio++;
				} else {
					noCambio = 0;
				}
			} else {
				noCambio = 5;
			}
		}
		return mejorColoreo;
	}

	private Coloreo getProximoColoreo(Coloreo colores) {
		Coloreo nuevoColoreo;
		Coloreo mejorColoreo = colores;
		
		for (Conflicto c : colores.getConflictos()) {	
			ArrayList<Color> nuevosColores = new ArrayList<Color>(colores.getColores());
			TreeSet<Integer> coloresOcupados = new TreeSet<Integer>();
			NodoMateria nodoActual = grafo.getMateria(c.getId());
			
			for (int i = 0; i < c.getConflictos().size(); i++) {
				NodoMateria vecino = c.getConflictos().get(i);
				List<Color> coloresSeteados = colores.getColores(); 
				int color = coloresSeteados.get( vecino.getId() ).getColor();
				System.out.println("color: " + color);
				coloresOcupados.add(color);
			}
/*			for (NodoMateria vecino : c.getConflictos()) {
				
			}
	*/		
			ArrayList<Integer> coloresPosibles = new ArrayList<Integer>(nodoActual.getColoresPosibles());
			if (!coloresPosibles.isEmpty()) {
				Color actual = new Color(nodoActual.getId(), coloresPosibles.get(0));
				
				nuevosColores.set(nodoActual.getId(), actual);
				nuevoColoreo = new Coloreo(grafo, nuevosColores);
				if (nuevoColoreo.cantidadDeConflictos() <= mejorColoreo.cantidadDeConflictos()) {
					mejorColoreo = nuevoColoreo;
				} 
			}
		}
		
		return mejorColoreo;
	}
		

}
