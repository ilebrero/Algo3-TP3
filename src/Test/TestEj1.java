package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import ejercicio1.Ejercicio1;
import ejercicio3.Ejercicio3;
import utils.Color;
import utils.Coloreo;
import utils.Componente;
import utils.GrafoMaterias;
import utils.GrafoPredicados;
import utils.NodoPredicado;
import utils.NodoMateria;
import utils.Tuple;

public class TestEj1 {
	
	@Test
	public void testEjesCrecientes(){
		GrafoPredicados grafo = generarCiclo(200);
		ArrayList<Tuple<Integer,Integer>> aristas = aristasCiclo(200);
		
		for (int i = 0; i < 1000; i++) {
			Ejercicio1 ej = new Ejercicio1(grafo.size());
			ej.solve(grafo);
		}
		
		int cantAristas = aristas.size();
		for (int i = 0; i < cantAristas; i++) {
			Tuple<Integer, Integer> tuple = aristas.get(0);
			aristas.remove(0);
			grafo.connectMateria(tuple.getX(), tuple.getY());
			double tiempoFinal = 0;
			for (int j = 0; j < 3; j++) {
				Ejercicio1 ej = new Ejercicio1(grafo.size());
				double tiempo = System.nanoTime();
				ej.solve(grafo);
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
			}
			System.out.println(tiempoFinal);
		}
		
		
	}
	
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
	
	@Test
	public void testkosarajuSinSolucion() {
		GrafoPredicados grafo = new GrafoPredicados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(1);
		coloresMateria1.add(2);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(0);
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

			//@Test
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
		public void experimentoNodosEstaticosEjesCrecientesProba50() {
			double tiempo;
			Generador g = new Generador();
			
			for(int i = 0; i < 1000000; ++i) {
				Math.random();
			}
			
			for (int ejes = 1; ejes < 9000; ++ejes) {
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
	public void experimentoNodosEstaticosEjesCrecientesProba1() {
		double tiempo;
		Generador g = new Generador();
		
		for(int i = 0; i < 1000000; ++i) {
			Math.random();
		}
		
		for (int ejes = 1; ejes < 8000; ++ejes) {
			GrafoPredicados grafo = new GrafoPredicados();
			
			ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, 300, 3);

			for (NodoMateria n : nodos) {
				grafo.addMateria(n);
			}
			
			Ejercicio1 ej = new Ejercicio1(grafo.size());
						
			g.generarConexiones(grafo, ejes, 1);
			
			tiempo = System.nanoTime();
			ArrayList<Color> solucion = ej.solve(grafo);
			tiempo = (System.nanoTime() - tiempo)/1000;
			
			System.out.println(tiempo);
			//mostrarResultado(solucion);
		}
	}

	//@Test
	public void experimentoNodosEstaticosEjesCrecientesProba100() {
		double tiempo;
		Generador g = new Generador();
		
		for(int i = 0; i < 1000000; ++i) {
			Math.random();
		}
		
		for (int ejes = 1; ejes < 8000; ++ejes) {
			GrafoPredicados grafo = new GrafoPredicados();
			
			ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, 300, 3);

			for (NodoMateria n : nodos) {
				grafo.addMateria(n);
			}
			
			Ejercicio1 ej = new Ejercicio1(grafo.size());
						
			g.generarConexiones(grafo, ejes, 100);
			
			tiempo = System.nanoTime();
			ArrayList<Color> solucion = ej.solve(grafo);
			tiempo = (System.nanoTime() - tiempo)/1000;
			
			System.out.println(tiempo);
			//mostrarResultado(solucion);
		}
	}
	
	//@Test
	public void experimentoNodosEstaticosEjesCrecientesProba75() {
		double tiempo;
		Generador g = new Generador();
		
		for(int i = 0; i < 1000000; ++i) {
			Math.random();
		}
		
		for (int ejes = 1; ejes < 44850; ++ejes) {
			GrafoPredicados grafo = new GrafoPredicados();
			
			ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, 300, 3);

			for (NodoMateria n : nodos) {
				grafo.addMateria(n);
			}
			
			Ejercicio1 ej = new Ejercicio1(grafo.size());
						
			g.generarConexiones(grafo, ejes, 75);
			
			tiempo = System.nanoTime();
			ArrayList<Color> solucion = ej.solve(grafo);
			tiempo = (System.nanoTime() - tiempo)/1000;
			
			System.out.println(tiempo);
			//mostrarResultado(solucion);
		}
	}
	
	//@Test
	public void experimentoNodosEstaticosEjesCrecientesProba25() {
		double tiempo;
		Generador g = new Generador();
		
		for(int i = 0; i < 1000000; ++i) {
			Math.random();
		}
		
		for (int ejes = 1; ejes < 8000; ++ejes) {
			GrafoPredicados grafo = new GrafoPredicados();
			
			ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(2, 300, 3);

			for (NodoMateria n : nodos) {
				grafo.addMateria(n);
			}
			
			Ejercicio1 ej = new Ejercicio1(grafo.size());
						
			g.generarConexiones(grafo, ejes, 25);
			
			tiempo = System.nanoTime();
			ArrayList<Color> solucion = ej.solve(grafo);
			tiempo = (System.nanoTime() - tiempo)/1000;
			
			System.out.println(tiempo);
			//mostrarResultado(solucion);
		}
	}
			
	@Test
		public void experimento100NodosunColor() {
			double tiempo;
			double promedio = 0;
			Generador g = new Generador();
			
			for(int i = 0; i < 1000000; ++i) {
				Math.random();
			}
			
			for (int inodos = 1; inodos < 44500; ++inodos) {
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
				
				System.out.println(tiempo);
				//mostrarResultado(solucion);
			}
			
//			promedio = promedio / 5000;
//			System.out.println("promedio: " + promedio);
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
			
			for (NodoPredicado nodo : componente.getNodos()) {
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
	private  GrafoPredicados generarCiclo(int i){
		 int aristas = 0;
		 Random randomGenerator = new Random();			  
		 GrafoPredicados grafo = new GrafoPredicados();
		   for (int j = 0; j <= i; j++) {
			   grafo.addMateria(new NodoMateria(randomGenerator.nextInt(100), randomGenerator.nextInt(100)));
		   }
//		   for (int j = 0; j < i; j++) {
//					  grafo.connectMateria(j, j+1);
//					  aristas++;
//		   }
		   grafo.connectMateria(0, i);
		   
	//	   System.out.println("Cantidad de aristas conectadas" + aristas);
		   return grafo;
	}
		   
	private ArrayList<Tuple<Integer,Integer>> aristasCiclo(int i){
		ArrayList<Tuple<Integer,Integer>> aristas = new ArrayList<Tuple<Integer,Integer>>();
		for (int j = 0; j < i; j++) {
			   for (int k = 0; k < j; k++) {
				   if (j != k){
					 aristas.add(new Tuple<Integer, Integer>(j,k));
				   }
			   }
		}
		Collections.shuffle(aristas);
		
		return aristas;
		
	}
}
