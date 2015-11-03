package Test;

import java.util.ArrayList;

import org.junit.Test;

import ejercicio1.Ejercicio1;
import utils.GrafoEstados;
import utils.NodoEstado;
import utils.NodoMateria;

public class TestEj1 {
	
	//@Test
	public void testkosarajuConSolucion() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria2));
		grafo.connectMateria(0, 1);
		grafo.generarGrafoDeEstados();
		
		ArrayList< ArrayList<NodoEstado> > resultado = Ejercicio1.kosaraju(grafo);
		
		for (ArrayList<NodoEstado> componente : resultado) {
			System.out.println("componente!! -> size: " + componente.size());
			
			for (NodoEstado nodo : componente) {
				System.out.println("id: " + nodo.getPadreId() + " | color: " + nodo.getColor() + " | negado? -> " + nodo.getNegado());
			}
		}
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
		coloresMateria3.add(2);
		coloresMateria3.add(1);
		
		grafo.addMateria(new NodoMateria(coloresMateria1));
		grafo.addMateria(new NodoMateria(coloresMateria2));
		grafo.addMateria(new NodoMateria(coloresMateria3));
		grafo.connectMateria(0, 1);
		grafo.connectMateria(1, 2);
		grafo.connectMateria(0, 2);
		grafo.generarGrafoDeEstados();
		
		ArrayList< ArrayList<NodoEstado> > resultado = Ejercicio1.kosaraju(grafo);
		
		for (ArrayList<NodoEstado> componente : resultado) {
			System.out.println("componente!! -> size: " + componente.size());
			
			for (NodoEstado nodo : componente) {
				System.out.println("id: " + nodo.getPadreId() + " | color: " + nodo.getColor() + " | negado? -> " + nodo.getNegado());
			}
		}
	}
}
