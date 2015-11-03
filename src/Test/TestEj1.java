package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import utils.GrafoEstados;
import utils.GrafoMaterias;
import utils.Materia;
import utils.NodoEstado;

public class TestEj1 {

	@Test
	public void testCreacion() {
		GrafoMaterias grafo = new GrafoMaterias();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
		grafo.addMateria(new Materia(coloresMateria1));
		grafo.addMateria(new Materia(coloresMateria2));
		grafo.connectMateria(0, 1);
	}
	
	@Test
	public void testCreacion2() {
		GrafoEstados grafo = new GrafoEstados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(0);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(3);
		
		grafo.addMateria(new Materia(coloresMateria1));
		grafo.addMateria(new Materia(coloresMateria2));
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
		
		grafo.addMateria(new Materia(coloresMateria1));
		grafo.addMateria(new Materia(coloresMateria2));
		grafo.connectMateria(0, 1);
		grafo.generarGrafoDeEstados();
		
		String s = "";
		for (NodoEstado e : grafo.getNodosEstado()) {
			s = s + "id: " + e.getId() + " | color: " + e.getColor() + " | negado? -> " + e.getNegado();
			s = s + "  conecta con:";
			for (NodoEstado r : e.getAdyacentes()) {
				s = s + "    id: " + r.getId() + " |color: " + r.getColor() + " | negado? -> " + r.getNegado();
			}
		}
		
		assertEquals(s, "id: 0 | color: 0 | negado? -> false  conecta con:    id: 1 |color: 1 | negado? -> false    id: 1 |color: 3 | negado? -> falseid: 0 | color: 1 | negado? -> false  conecta con:    id: 1 |color: 1 | negado? -> true    id: 1 |color: 3 | negado? -> falseid: 0 | color: 0 | negado? -> true  conecta con:id: 0 | color: 1 | negado? -> true  conecta con:id: 1 | color: 1 | negado? -> false  conecta con:    id: 0 |color: 0 | negado? -> false    id: 0 |color: 1 | negado? -> trueid: 1 | color: 3 | negado? -> false  conecta con:    id: 0 |color: 0 | negado? -> false    id: 0 |color: 1 | negado? -> falseid: 1 | color: 1 | negado? -> true  conecta con:id: 1 | color: 3 | negado? -> true  conecta con:");
	}

}
