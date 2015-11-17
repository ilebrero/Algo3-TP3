package Test;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import utils.GrafoPredicados;
import utils.Color;
import utils.NodoMateria;
import ejercicio2.Ejercicio2;

public class TestEj2 {

	//@Test
	public void test() {
		GrafoPredicados grafo = new GrafoPredicados();
		
		ArrayList<Integer> coloresMateria1 = new ArrayList<Integer>();
		coloresMateria1.add(1);
		coloresMateria1.add(2);
		coloresMateria1.add(3);
		coloresMateria1.add(4);
		
		ArrayList<Integer> coloresMateria2 = new ArrayList<Integer>();
		coloresMateria2.add(1);
		coloresMateria2.add(2);
		coloresMateria2.add(3);
		coloresMateria2.add(4);
		coloresMateria2.add(5);
		
		NodoMateria m1 = new NodoMateria(coloresMateria1);
		NodoMateria m2 = new NodoMateria(coloresMateria2);
		
		grafo.addMateria(m1);
		grafo.addMateria(m2);		
		
		grafo.connectMateria(0, 1);

		Ejercicio2 ej = new Ejercicio2(grafo);
		System.out.println(ej.solverWithBacktrack());
		
		for (int i = 0; i < grafo.getMaterias().size(); i++) {
			System.out.print(ej.getSolucion().get(i)+",");
		}
	}
	
	@Test
	public void experimentoColoresAleatorios() {
		GrafoPredicados grafo = new GrafoPredicados();
		
		ArrayList<NodoMateria> nodos = Generador.generarNodosConColoresPosibles(10, 9, 10);

		for (NodoMateria n : nodos) {
			grafo.addMateria(n);
		}

		/*grafo.connectMateria(0, 1);
		grafo.connectMateria(0, 8);
		grafo.connectMateria(0, 6);
		grafo.connectMateria(1, 2);
		grafo.connectMateria(1, 5);
		grafo.connectMateria(1, 6);
		grafo.connectMateria(2, 3);
		grafo.connectMateria(2, 5);
		grafo.connectMateria(5, 4);
		grafo.connectMateria(6, 7);*/
		
		Generador g = new Generador();
		
		g.generarConexiones(grafo, 10, 50);
		
		Ejercicio2 ej = new Ejercicio2(grafo);
		
		if (ej.solverWithBacktrack()) {
			ArrayList<Color> solucion = ej.getSolucion();
			System.out.println(solucion);
		} 
	}
	
	//@Test
	public void testSimple() {
		GrafoPredicados grafo = new GrafoPredicados();
		
		grafo.addMateria(new NodoMateria(0, true)); //coloreo valido :  	1
		grafo.addMateria(new NodoMateria(0, true)); //coloreo valido :  	0
		grafo.addMateria(new NodoMateria(0, true));
		grafo.connectMateria(0, 1);
		grafo.connectMateria(1, 2);
		grafo.connectMateria(0, 2);
		
		Ejercicio2 ej = new Ejercicio2(grafo);
		System.out.println(ej.solverWithBacktrack());
		
		 int[] ret = new int[ej.getSolucion().size()];
		    for (int i=0; i < ret.length; i++) //preguntar
		    {
		        ret[i] = ej.getSolucion().get(i).getColor();
		    }
		    
		    System.out.println("Intentos: " +ej.getIntentos());
		System.out.println("conflictos : " +grafo.findConflicts(ret));
		for (int i = 0; i < grafo.getMaterias().size(); i++) {
			System.out.print("color: " + ej.getSolucion().get(i).getColor() + " | id: " + ej.getSolucion().get(i).getId());
		}
	}
	
	@Test
	public void test2() {
		GrafoPredicados grafo = crearGrafoRompe2();
		Ejercicio2 ej = new Ejercicio2(grafo);
		System.out.println(ej.solverWithBacktrack());
		
		 int[] ret = new int[ej.getSolucion().size()];
		    for (int i=0; i < ret.length; i++)
		    {
		        ret[i] = ej.getSolucion().get(ret.length -1 - i).getColor();
		    }
		    
		    System.out.println("Intentos: " +ej.getIntentos());
		    System.out.println("Pode con la 1: " +ej.getPoda1());
		System.out.println("conflictos : " +grafo.findConflicts(ret));
		for (int i = 0; i < grafo.getMaterias().size(); i++) {
			System.out.print("color: " + ej.getSolucion().get(i).getColor() + " | id: " + ej.getSolucion().get(i).getId());
		}
	}
	
	@Test
	public void testKn() {
		
		for (int i = 0; i < 100; i++) {
			GrafoPredicados grafo = generarKn(4);
			Ejercicio2 ej = new Ejercicio2(grafo);
			ej.solverWithBacktrack();
		}
		

		for (int i = 0; i < 1000; i++) {

			GrafoPredicados grafo = generarKn(i);
			Ejercicio2 ej = new Ejercicio2(grafo);
			double tiempo = System.nanoTime();
			ej.solverWithBacktrack();
			tiempo = (System.nanoTime() - tiempo)/1000;

//			System.out.println(ej.getPoda1()+";");
			System.out.println(tiempo+";");
			//System.out.println(ej.getPoda1()+";");
			System.out.println(tiempo);
		}
		
		
		
//		 int[] ret = new int[ej.getSolucion().size()];
//		    for (int i=0; i < ret.length; i++)
//		    {
//		        ret[i] = ej.getSolucion().get(ret.length -1 - i).getColor();
//		    }
//		    
//		    System.out.println("Intentos: " +ej.getIntentos());
//		    System.out.println("Pode con la 1: " +ej.getPoda1());
//		System.out.println("conflictos : " +grafo.findConflicts(ret));
//		for (int i = 0; i < grafo.getMaterias().size(); i++) {
//			System.out.print(ej.getSolucion().get(i)+",");
//		}
	}
	

	
	private GrafoPredicados crearGrafoRompe2(){
		GrafoPredicados grafo = new GrafoPredicados();
		
		// Rojo : 0 , Rosa : 1 , Bordo : 2, Verde : 3 ,Azul : 4
		grafo.addMateria(new NodoMateria(1, 0,0)); //coloreo valido :  	1
		grafo.addMateria(new NodoMateria(0, 0,0)); //coloreo valido :  	0
		grafo.addMateria(new NodoMateria(2, 0,0)); //coloreo valido :  	2
		grafo.addMateria(new NodoMateria(2, 0,0)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(1, 0,0)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(2, 0,0)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(2, 0,0)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(1, 0,0)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(3, 0,0)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3, 0,0)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(4, 0,0)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(4, 0,0)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0, 0,0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(4, 0,0)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0, 0,0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(2, 0,0)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(0, 0,0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(1, 0,0)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(3, 0,0)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(0, 0,0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(4, 0,0)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(3, 0,0)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(1, 0,0)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(1, 0,0)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(4, 0,0)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(2, 0,0)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(4, 0,0)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0, 0,0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(3, 0,0)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3, 0,0)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3, 0,0)); //coloreo valido :	3
		
		grafo.connectMateria(0, 1);
		grafo.connectMateria(6, 13);
		grafo.connectMateria(6, 30);
		grafo.connectMateria(6, 27);
		grafo.connectMateria(6, 17);
		grafo.connectMateria(17, 27);
		grafo.connectMateria(17, 29);
		grafo.connectMateria(17, 12);
		grafo.connectMateria(12, 29);
		grafo.connectMateria(12, 4);
		grafo.connectMateria(12,18 );
		grafo.connectMateria(4, 25);
		grafo.connectMateria(4, 18);
		grafo.connectMateria(18,10 );
		grafo.connectMateria(25,11 );
		grafo.connectMateria(25, 8);
		grafo.connectMateria(25, 9);
		grafo.connectMateria(25, 24);
		grafo.connectMateria(11,1 );
		grafo.connectMateria(11, 19);
		grafo.connectMateria(11, 8);
		grafo.connectMateria(19, 8);
		grafo.connectMateria(19, 10);
		grafo.connectMateria(8, 10);
		grafo.connectMateria(10,1 );
		grafo.connectMateria(1,7 );
		grafo.connectMateria(1,9 );
		grafo.connectMateria(9, 7);
		grafo.connectMateria(7, 20);
		grafo.connectMateria(7, 24);
		grafo.connectMateria(9,20 );
		grafo.connectMateria(20,22 );
		grafo.connectMateria(20, 16);
		grafo.connectMateria(16, 22);
		grafo.connectMateria(16, 24);
		grafo.connectMateria(16, 28);
		grafo.connectMateria(28,20 );
		grafo.connectMateria(28, 3);
		grafo.connectMateria(3, 22);
		grafo.connectMateria(3, 24);
		grafo.connectMateria(24, 29);
		grafo.connectMateria(3, 21);
		grafo.connectMateria(28, 15);
		grafo.connectMateria(21, 26);
		grafo.connectMateria(15,29 );
		grafo.connectMateria(15, 14);
		grafo.connectMateria(26, 29);
		grafo.connectMateria(26, 2);
		grafo.connectMateria(26, 5);
		grafo.connectMateria(5, 14);
		grafo.connectMateria(5, 23);
		grafo.connectMateria(23,2 );
		grafo.connectMateria(2, 14);
		grafo.connectMateria(2, 30);
		grafo.connectMateria(2, 13);
		grafo.connectMateria(13,30 );
		grafo.connectMateria(13, 27);
		grafo.connectMateria(27,30 );
		return grafo;
		}

	public  GrafoPredicados generarKn(int i){
	 	   GrafoPredicados grafo = new GrafoPredicados();
	 	   for (int j = 0; j < i; j++) {
	 		  grafo.addMateria(new NodoMateria(i,0,0));
	 	   }
	 	   for (int j = 0; j < i; j++) {
	 		   for (int k = 0; k < i; k++) {
	 			   if (j != k){
//	 			   System.out.println("conecto " + j + "con "+ k);
	 				  grafo.connectMateria(j, k);
	 			   }
	 		   }
	 	   }
	 	   return grafo;
	    }
	
	private  GrafoPredicados generarPseudoKn(int i){
		Random randomGenerator = new Random();
		
		 int aristas = 0;
		 GrafoPredicados grafo = new GrafoPredicados();
		   for (int j = 0; j < i; j++) {
			   grafo.addMateria(new NodoMateria(i,0,0));
		   }
		  grafo.addMateria(new NodoMateria(1, true));
		   for (int j = 0; j < i; j++) {
			   for (int k = randomGenerator.nextInt(j + 1); k < randomGenerator.nextInt(j + 1); k++) {
				   if (j != k){
//				   System.out.println("conecto " + j + "con "+ k);
					  grafo.connectMateria(j, k);
					  aristas++;
				   }
			   }
		   }
//		   System.out.println("Cantidad de aristas conectadas" + aristas);
		   return grafo;
	 }
}
