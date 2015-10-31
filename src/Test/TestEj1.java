package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import exercises3.Grafo;
import exercises3.Materia;

public class TestEj1 {

	@Test
	public void test() {
		Grafo grafo = new Grafo();
		grafo.addMateria(new Materia(0, 1, 2));
		grafo.addMateria(new Materia(1, 3, 4));
		grafo.connectMateria(0, 1);
	}

}
