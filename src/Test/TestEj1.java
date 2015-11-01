package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.Grafo;
import utils.Materia;

public class TestEj1 {

	@Test
	public void test() {
		Grafo grafo = new Grafo();
		grafo.addMateria(new Materia(0, 1));
		grafo.addMateria(new Materia(1, 3));
		grafo.connectMateria(0, 1);
	}

}
