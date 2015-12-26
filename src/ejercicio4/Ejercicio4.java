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
	private int diffconflictos;
	int iteraciones = 0;
	int mejoro = 0;
	
	public int getIteraciones() {
		return iteraciones;
	}

	public int getMejoro() {
		return mejoro;
	}

	public Ejercicio4(GrafoPredicados grafo) {
		this.grafo = grafo;
		ej3 = new Ejercicio3(grafo);
		ej3.solve();
		System.out.print(ej3.checkColoreo()+",");
		diffconflictos = 0;
	}
	
	public Coloreo solve() {
		ArrayList<Color> colores = new ArrayList<Color>(); 
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
	
	public Coloreo solveVecindad2() {
		ArrayList<Color> colores = new ArrayList<Color>(); 
		int [] vectorColores = ej3.getColoreo();
		
		for (int i = 0; i < vectorColores.length; ++i) {
			Color actual = new Color(vectorColores[i], i);
			colores.add(actual);
		}
		
		coloreoActual = new Coloreo(grafo, colores);
		
		if (coloreoActual.esValido()) {
			return coloreoActual;
		} else {
			return resolveVecindad2(coloreoActual);
		}
	}
		
	public Coloreo solve2() {
		ArrayList<Color> colores = new ArrayList<Color>(); 
		ej3.solve2();
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
	
	public Coloreo solve3() {
		ArrayList<Color> colores = new ArrayList<Color>(); 
		ej3.solve3();
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
	public Coloreo solveSinPensar() {
		ArrayList<Color> colores = new ArrayList<Color>(); 
		ej3.solveSinPensar();
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
		
		while (!mejorColoreo.esValido() && noCambio < 5) {
			nuevoColoreo = getProximoColoreo(mejorColoreo);
			
			if (nuevoColoreo.cantidadDeConflictos() <= mejorColoreo.cantidadDeConflictos()) {
				mejorColoreo = nuevoColoreo;
				iteraciones++;
				
				if (nuevoColoreo.cantidadDeConflictos() == mejorColoreo.cantidadDeConflictos()) {
					noCambio++;
				} else {
					mejoro++;
					noCambio = 0;
				}
			} else {
				noCambio = 5;
			}
		}
		
		return mejorColoreo;
	}
	
	private Coloreo resolveVecindad2(Coloreo colores) {
		int noCambio = 0;
		Coloreo nuevoColoreo;
		Coloreo mejorColoreo = colores;
		
		while (!mejorColoreo.esValido() && noCambio < 5) {
			nuevoColoreo = getProximoColoreoDisferenteVecindad(mejorColoreo);
			
			if (nuevoColoreo.cantidadDeConflictos() <= mejorColoreo.cantidadDeConflictos()) {
				mejorColoreo = nuevoColoreo;
				iteraciones++;
				
				if (nuevoColoreo.cantidadDeConflictos() == mejorColoreo.cantidadDeConflictos()) {
					noCambio++;
				} else {
					mejoro++;
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
			
			for(NodoMateria vecino : nodoActual.getAdyacentes()) {
				List<Color> coloresSeteados = colores.getColores(); 
				int color = coloresSeteados.get( vecino.getId() ).getColor();
			
				coloresOcupados.add(color);
			}

			ArrayList<Integer> coloresPosibles = new ArrayList<Integer>(nodoActual.getColoresPosibles());
			coloresPosibles.removeAll(coloresOcupados);
			
			if (!coloresPosibles.isEmpty()) {
				Color actual = new Color(coloresPosibles.get(0), nodoActual.getId());
				
				nuevosColores.set(nodoActual.getId(), actual);
				nuevoColoreo = new Coloreo(grafo, nuevosColores);
				
				if (nuevoColoreo.cantidadDeConflictos() < mejorColoreo.cantidadDeConflictos()) {
					mejorColoreo = nuevoColoreo;
				} 
			}
		}
		
		return mejorColoreo;
	}
	
	private Coloreo getProximoColoreoDisferenteVecindad(Coloreo colores) {
		Coloreo nuevoColoreo;
		Coloreo mejorColoreo = colores;
		
		for (Conflicto c : colores.getConflictos()) {
			ArrayList<Color> nuevosColores   = new ArrayList<Color>(mejorColoreo.getColores());
			TreeSet<Integer> coloresOcupados = new TreeSet<Integer>();
			NodoMateria nodoActual = grafo.getMateria(c.getId());
			
			for(NodoMateria vecino : nodoActual.getAdyacentes()) {
				List<Color> coloresSeteados = colores.getColores(); 
				int color = coloresSeteados.get( vecino.getId() ).getColor();
			
				coloresOcupados.add(color);
			}

			ArrayList<Integer> coloresPosibles = new ArrayList<Integer>(nodoActual.getColoresPosibles());
			coloresPosibles.removeAll(coloresOcupados);
			
			if (!coloresPosibles.isEmpty()) {
				Color actual = new Color(coloresPosibles.get(0), nodoActual.getId());
				
				nuevosColores.set(nodoActual.getId(), actual);
				nuevoColoreo = new Coloreo(grafo, nuevosColores);
				
				if (nuevoColoreo.cantidadDeConflictos() < mejorColoreo.cantidadDeConflictos()) {
					mejorColoreo = nuevoColoreo;
				} 
			}
		}
		
		return mejorColoreo;
	}
}
