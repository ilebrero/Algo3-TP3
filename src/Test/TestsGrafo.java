package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import utils.GrafoEstados;
import utils.GrafoMaterias;
import utils.NodoEstado;
import utils.NodoMateria;

public class TestsGrafo {

	//@Test
	public void testCreacion() {
		GrafoMaterias grafo = new GrafoMaterias();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
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
	}
	
	//@Test
	public void testCreacionEstados() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
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
	}
	
	@Test
	public void testConexiones() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
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
		
		String s = "";
		for (NodoEstado e : grafo.getNodosEstado()) {
			s = s + "id: " + e.getPadreId() + " | color: " + e.getColor() + " | negado? -> " + e.getNegado();
			s = s + "  conecta con:";
			for (NodoEstado r : e.getAdyacentes()) {
				s = s + "    id: " + r.getPadreId() + " |color: " + r.getColor() + " | negado? -> " + r.getNegado();
			}
		}
		
		assertEquals(s, "id: 0 | color: 0 | negado? -> false  conecta con:    id: 0 |color: 1 | negado? -> trueid: 0 | color: 1 | negado? -> false  conecta con:    id: 0 |color: 0 | negado? -> true    id: 1 |color: 1 | negado? -> trueid: 0 | color: 0 | negado? -> true  conecta con:    id: 0 |color: 1 | negado? -> falseid: 0 | color: 1 | negado? -> true  conecta con:    id: 0 |color: 0 | negado? -> falseid: 1 | color: 1 | negado? -> false  conecta con:    id: 1 |color: 3 | negado? -> true    id: 0 |color: 1 | negado? -> trueid: 1 | color: 3 | negado? -> false  conecta con:    id: 1 |color: 1 | negado? -> trueid: 1 | color: 1 | negado? -> true  conecta con:    id: 1 |color: 3 | negado? -> falseid: 1 | color: 3 | negado? -> true  conecta con:    id: 1 |color: 1 | negado? -> false");
	}
	
	//@Test
	public void testInversion() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
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
		
		String sInvertido = "";
		for (NodoEstado e : grafo.grafoInvertido()) {
			sInvertido = sInvertido + "nuevo -> id: " + e.getPadreId() + " | color: " + e.getColor() + " | negado? -> " + e.getNegado();
			sInvertido = sInvertido + "  conecta con:";
			for (NodoEstado r : e.getAdyacentes()) {
				sInvertido = sInvertido + "    id: " + r.getPadreId() + " |color: " + r.getColor() + " | negado? -> " + r.getNegado();
			}
		}
		
		assertEquals(sInvertido, "id: 0 | color: 0 | negado? -> false  conecta con:    id: 0 |color: 1 | negado? -> trueid: 0 | color: 1 | negado? -> false  conecta con:    id: 0 |color: 0 | negado? -> true    id: 1 |color: 1 | negado? -> trueid: 0 | color: 0 | negado? -> true  conecta con:    id: 0 |color: 1 | negado? -> falseid: 0 | color: 1 | negado? -> true  conecta con:    id: 0 |color: 0 | negado? -> falseid: 1 | color: 1 | negado? -> false  conecta con:    id: 1 |color: 3 | negado? -> true    id: 0 |color: 1 | negado? -> trueid: 1 | color: 3 | negado? -> false  conecta con:    id: 1 |color: 1 | negado? -> trueid: 1 | color: 1 | negado? -> true  conecta con:    id: 1 |color: 3 | negado? -> falseid: 1 | color: 3 | negado? -> true  conecta con:    id: 1 |color: 1 | negado? -> false");
	}
}