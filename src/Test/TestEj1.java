package Test;

import java.util.ArrayList;

import org.junit.Test;

import ejercicio1.Componente;
import ejercicio1.Ejercicio1;
import utils.GrafoEstados;
import utils.NodoEstado;
import utils.NodoMateria;

public class TestEj1 {
	
	@Test
	public void testkosarajuConSolucion() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(0);
		
		NodoMateria m1 = new NodoMateria(coloresMateria1);
		NodoMateria m2 = new NodoMateria(coloresMateria2);
		
		grafo.addMateria(m1);
		grafo.addMateria(m2);		

		for (NodoMateria materia : grafo.getMaterias()) {
			for (Integer i : materia.getColoresPosibles()) {
				materia.setColor(i);
			}
		}
		
		grafo.connectMateria(0, 1);
		grafo.generarGrafoDeEstados();
		
		ArrayList< Componente > resultado = Ejercicio1.kosaraju(grafo);
		ArrayList< ArrayList <Integer> > solucion = Ejercicio1.solve(grafo);
		
		mostrarResultado(solucion);
		mostrarComponentes(resultado);
	}
	
	@Test
	public void testkosarajuSinSolucion() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(1);
		coloresMateria1.add(2);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(2);
		
		ArrayList<Integer> coloresMateria3 = new ArrayList<Integer>();
		coloresMateria3.add(1);
		coloresMateria3.add(2);
		
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria2));
		grafo.addMateria(new NodoMateria(coloresMateria3));
		
		for (NodoMateria materia : grafo.getMaterias()) {
			for (Integer i : materia.getColoresPosibles()) {
				materia.setColor(i);
			}
		}
		
		grafo.connectMateria(0, 1);
		grafo.connectMateria(1, 2);
		grafo.connectMateria(0, 2);
		grafo.generarGrafoDeEstados();
		
		ArrayList< Componente > resultado = Ejercicio1.kosaraju(grafo);
		ArrayList< ArrayList <Integer> > solucion = Ejercicio1.solve(grafo);

		mostrarComponentes(resultado);
		mostrarResultado(solucion);
	}
	
	@Test
	public void testkosarajuConSolu2() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(1);
		coloresMateria1.add(2);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
		
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria2));
		
		for (NodoMateria materia : grafo.getMaterias()) {
			for (Integer i : materia.getColoresPosibles()) {
				materia.setColor(i);
			}
		}
		
		grafo.connectMateria(0, 1);
		grafo.connectMateria(0, 8);
		grafo.connectMateria(0, 6);
		grafo.connectMateria(1, 2);
		grafo.connectMateria(1, 5);
		grafo.connectMateria(1, 6);
		grafo.connectMateria(2, 3);
		grafo.connectMateria(2, 5);
		grafo.connectMateria(5, 4);
		grafo.connectMateria(6, 7);
		grafo.generarGrafoDeEstados();
		
		ArrayList< Componente > resultado = Ejercicio1.kosaraju(grafo);
		ArrayList< ArrayList<Integer> > solucion = Ejercicio1.solve(grafo);
		
		mostrarComponentes(resultado);
		mostrarResultado(solucion);
	}
	
	private void mostrarComponentes(ArrayList<Componente> resultado) {
		for (Componente componente : resultado) {
			System.out.println("componente!! -> size: " + componente.getNodos().size());
			
			for (NodoEstado nodo : componente.getNodos()) {
				System.out.println("id: " + nodo.getPadreId() + " | color: " + nodo.getColor() + " | negado? -> " + nodo.getNegado());
			}
		}
	}
	
	private void mostrarResultado(ArrayList< ArrayList <Integer> > solucion) {
		if (solucion != null) {
			System.out.println(solucion);			
		} else {
			System.out.println("no hay solicion :(");
		}
	}
}
