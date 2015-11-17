package Test;

import java.util.ArrayList;
import org.junit.Test;

import ejercicio1.Ejercicio1;
import utils.Color;
import utils.Coloreo;
import utils.Componente;
import utils.GrafoPredicados;
import utils.NodoEstado;
import utils.NodoMateria;

public class TestEj1 {
	
	//@Test
	public void testkosarajuConSolucion() {
		GrafoPredicados grafo = new GrafoPredicados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(2);
		coloresMateria1.add(1);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(2);
		
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

		Ejercicio1 ej = new Ejercicio1(grafo.size());
		ArrayList <Color> solucion = ej.solve(grafo);		
		
		mostrarResultado(solucion);
	}
	
	//@Test
	public void testkosarajuSinSolucion() {
		GrafoPredicados grafo = new GrafoPredicados();
		
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
		
		Ejercicio1 ej = new Ejercicio1(grafo.size());
		ArrayList< Componente > resultado = ej.kosaraju(grafo);
		ArrayList <Color> solucion = ej.solve(grafo);
		
		if (solucion != null) {
			Coloreo c = new Coloreo(grafo, solucion);
		}

		mostrarComponentes(resultado);
		mostrarResultado(solucion);
	}
	
	//@Test
	public void testkosarajuConSolu2() {
		GrafoPredicados grafo = new GrafoPredicados();
		
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
		
		Ejercicio1 ej = new Ejercicio1(grafo.size());
		
		ArrayList<Color> solucion = ej.solve(grafo);
		
		mostrarResultado(solucion);
	}
	
	//@Test
	public void experimentoColoresAleatorios() {
		GrafoPredicados grafo = new GrafoPredicados();
		
		ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, 8, 3);

		for (NodoMateria n : nodos) {
			grafo.addMateria(n);
		}

		//grafo.connectMateria(0, 1);
		/*grafo.connectMateria(0, 8);
		grafo.connectMateria(0, 6);
		grafo.connectMateria(1, 2);
		grafo.connectMateria(1, 5);
		grafo.connectMateria(1, 6);
		grafo.connectMateria(2, 3);
		grafo.connectMateria(2, 5);
		grafo.connectMateria(5, 4);
		grafo.connectMateria(6, 7);
		*/
		Generador g = new Generador();
		g.generarConexiones(grafo, 9, 50);
		
		Ejercicio1 ej = new Ejercicio1(grafo.size());
		
		ArrayList<Color> solucion = ej.solve(grafo);
		
		mostrarResultado(solucion);
	}
	
	//@Test
	public void experimentoNodosCrecientesSinConexiones() {
		double tiempo;
		
		for (int inodos = 1; inodos < 10000; ++inodos) {
			GrafoPredicados grafo = new GrafoPredicados();
			
			ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, inodos, 3);

			for (NodoMateria n : nodos) {
				grafo.addMateria(n);
			}
			
			Ejercicio1 ej = new Ejercicio1(grafo.size());
			
			tiempo = System.nanoTime();
			ArrayList<Color> solucion = ej.solve(grafo);
			tiempo = (System.nanoTime() - tiempo)/1000;
			
			System.out.println(tiempo);
			//mostrarResultado(solucion);
		}
	}
	
	//@Test
			public void experimentoNodosCrecientesconEjesEstaticos() {
				double tiempo;
				Generador g = new Generador();
				
				for(int i = 0; i < 1000000; ++i) {
					Math.random();
				}
				
				for (int i = 1; i < 5000; ++i) {
					GrafoPredicados grafo = new GrafoPredicados();
					
					ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, i, 3);

					for (NodoMateria n : nodos) {
						grafo.addMateria(n);
					}
					
					Ejercicio1 ej = new Ejercicio1(grafo.size());
				
					
					g.generarConexiones(grafo, ((i*(i-1)/2) -1) / 2, 50);
					
					tiempo = System.nanoTime();
					ArrayList<Color> solucion = ej.solve(grafo);
					tiempo = (System.nanoTime() - tiempo)/1000;
					
					System.out.println(tiempo);
					//mostrarResultado(solucion);
				}
			}

			@Test
			public void experimentoNodosCrecientesconEjesAleatorios() {
				double tiempo;
				Generador g = new Generador();
				
				for(int i = 0; i < 1000000; ++i) {
					Math.random();
				}
				
				for (int i = 1; i < 5000; ++i) {
					GrafoPredicados grafo = new GrafoPredicados();
					
					ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, i, 3);

					for (NodoMateria n : nodos) {
						grafo.addMateria(n);
					}
					
					Ejercicio1 ej = new Ejercicio1(grafo.size());
				
					int ejes = (int) Math.random() * ( ((i*(i-1))/2) - 2);
					g.generarConexiones(grafo, ejes, 50);
					
					tiempo = System.nanoTime();
					ArrayList<Color> solucion = ej.solve(grafo);
					tiempo = (System.nanoTime() - tiempo)/1000;
					
					System.out.println(tiempo);
					//mostrarResultado(solucion);
				}
			}

			
	//@Test
		public void experimentoNodosEstaticosEjesCrecientes() {
			double tiempo;
			Generador g = new Generador();
			
			for(int i = 0; i < 1000000; ++i) {
				Math.random();
			}
			
			for (int ejes = 44000; ejes < 44850; ++ejes) {
				GrafoPredicados grafo = new GrafoPredicados();
				
				ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, 300, 3);

				for (NodoMateria n : nodos) {
					grafo.addMateria(n);
				}
				
				Ejercicio1 ej = new Ejercicio1(grafo.size());
			
				
				g.generarConexiones(grafo, ejes, 50);
				
				tiempo = System.nanoTime();
				ArrayList<Color> solucion = ej.solve(grafo);
				tiempo = (System.nanoTime() - tiempo)/1000;
				
				System.out.println(tiempo);
				//mostrarResultado(solucion);
			}
		}
			
	//@Test
		public void experimento100NodosunColor() {
			double tiempo;
			double promedio = 0;
			Generador g = new Generador();
			
			for(int i = 0; i < 1000000; ++i) {
				Math.random();
			}
			
			for (int inodos = 1; inodos < 10000; ++inodos) {
				GrafoPredicados grafo = new GrafoPredicados();
				
				ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(1, 100, 1);

				for (NodoMateria n : nodos) {
					grafo.addMateria(n);
				}
				
				g.generarConexiones(grafo, 99, 50);
				
				Ejercicio1 ej = new Ejercicio1(grafo.size());
				
				tiempo = System.nanoTime();
				ArrayList<Color> solucion = ej.solve(grafo);
				tiempo = (System.nanoTime() - tiempo)/1000;
				
				promedio += tiempo;
				
				//System.out.println(tiempo);
				//mostrarResultado(solucion);
			}
			
			promedio = promedio / 5000;
			System.out.println("promedio: " + promedio);
		}
	
	//@Test
	public void experimento50NodosdosColores() {
		double tiempo, promedio = 0;
		Generador g = new Generador();
		
		for(int i = 0; i < 1000000; ++i) {
			Math.random();
		}
		
		for (int inodos = 1; inodos < 10000; ++inodos) {
			GrafoPredicados grafo = new GrafoPredicados();
			
			ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, 50, 3);

			for (NodoMateria n : nodos) {
				grafo.addMateria(n);
			}
			
			g.generarConexiones(grafo, 49, 50);
			
			Ejercicio1 ej = new Ejercicio1(grafo.size());
			
			tiempo = System.nanoTime();
			ArrayList<Color> solucion = ej.solve(grafo);
			tiempo = (System.nanoTime() - tiempo)/1000;
			
			promedio += tiempo;
			//System.out.println(tiempo);
			//mostrarResultado(solucion);
		}
		
		promedio = promedio / 5000;
		System.out.println("promedio: " + promedio);
	}
	
	private void mostrarComponentes(ArrayList<Componente> resultado) {
		for (Componente componente : resultado) {
			System.out.println("componente!! -> size: " + componente.getNodos().size());
			
			for (NodoEstado nodo : componente.getNodos()) {
				System.out.println("id: " + nodo.getPadreId() + " | color: " + nodo.getColor() + " | negado? -> " + nodo.getNegado());
			}
		}
	}
	
	private void mostrarResultado(ArrayList <Color> solucion) {
		if (solucion != null) {
			System.out.println("hay solucion y es: ");
			for (Color c : solucion) {
				System.out.println("color:" + c.getColor() + " | Materia:" + c.getId());			
			}
		} else {
			System.out.println("no hay solicion :(");
		}
	}
}
