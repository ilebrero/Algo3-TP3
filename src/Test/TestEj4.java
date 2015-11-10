package Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

import ejercicio3.Ejercicio3;
import ejercicio4.Ejercicio4;
import utils.Color;
import utils.Coloreo;
import utils.Coloreo.Conflicto;
import utils.GrafoPredicados;
import utils.NodoMateria;

public class TestEj4 {
	private GrafoPredicados grafo =  crearGrafo();
	private GrafoPredicados grafoRompe2 =  crearGrafoRompe2();
	
	public TestEj4(){
		
	}
	
	@Test
	public void test() {
	/*try
      {
         FileOutputStream fileOut =
         new FileOutputStream("/home/dfixel/workspace/Algo3-TP3/src/grafos/grafo-ej4.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(grafo);
         out.close();
         fileOut.close();

      }catch(IOException i)
      {
          i.printStackTrace();
      }*/
		
		ArrayList<Coloreo> pruebas = new ArrayList<Coloreo>();
		
		pruebas.add(new Ejercicio4(grafo).solve());
		pruebas.add(new Ejercicio4(grafoRompe2).solve());
		pruebas.add(new Ejercicio4(this.grafoTest2()).solve());
	
		System.out.println("Conflictos con grafo 1:" + new Ejercicio3(grafo).checkColoreo()); 
		
		
		mostrarConflictos(pruebas.get(0));
		mostrarSolucion(pruebas.get(0));
		
		System.out.println("Conflictos con grafo 2:" + new Ejercicio3(grafoRompe2).checkColoreo()); 
		mostrarConflictos(pruebas.get(1));
		mostrarSolucion(pruebas.get(1));
		
		System.out.println("Conflictos con grafo 3 :" + new Ejercicio3(this.grafoTest2()).checkColoreo()); 
		mostrarConflictos(pruebas.get(2));
		mostrarSolucion(pruebas.get(2));
		
		
	}

	private void mostrarSolucion(Coloreo coloreo) {
		for (Color c : coloreo.getColores()) {
			System.out.println("id: " + c.getId() + " | color: " + c.getColor());
		}
		System.out.println();
		
	}
	
	private void mostrarConflictos(Coloreo coloreo) {
		System.out.println("conflictos: " + coloreo.cantidadDeConflictos());
		System.out.println("son: " + coloreo.getConflictos().size());
		for (Conflicto c : coloreo.getConflictos()) {
			System.out.println("id: " + c.getId());
		}
		System.out.println();
		
	}

	
	/*@Test
	public void test2() {
		 System.out.println("Conflictos con grafo 1 :" + new Ejercicio4(grafo).checkColoreoV2()); 
		 System.out.println("Conflictos con grafo 2 :" + new Ejercicio4(grafoRompe2).checkColoreoV2()); 
		 System.out.println("Conflictos con grafo 3 :" + new Ejercicio4(this.grafoTest2()).checkColoreoV2()); 
	}*/


	private GrafoPredicados grafoTest2(){
		GrafoPredicados grafo = new GrafoPredicados();
		int i = 0;
			grafo.addMateria(new NodoMateria(0, 1)); //coloreo valido :  	1
			grafo.addMateria(new NodoMateria(0, 1)); //coloreo valido :  	0
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 2)); //coloreo valido :		2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.addMateria(new NodoMateria(0, 0)); //coloreo valido :  	2
			grafo.connectMateria(i, i+1);
			grafo.connectMateria(i+1, i+2);
			grafo.connectMateria(i, i+3);
			for (int j = 4; j < 13; j++){
				grafo.connectMateria(1, j);
			}
		return grafo;
	}
	
	private GrafoPredicados crearGrafo(){
		GrafoPredicados grafo = new GrafoPredicados();
		
		// Rojo : 0 , Rosa : 1 , Bordo : 2, Verde : 3 ,Azul : 4
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :  	1
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :  	0
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :  	2
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		
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
	
	private GrafoPredicados crearGrafoRompe2(){
		GrafoPredicados grafo = new GrafoPredicados();
		
		// Rojo : 0 , Rosa : 1 , Bordo : 2, Verde : 3 ,Azul : 4
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :  	1
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :  	0
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :  	2
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(1)); //coloreo valido :	1
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(2)); //coloreo valido :	2
		grafo.addMateria(new NodoMateria(4)); //coloreo valido :	4
		grafo.addMateria(new NodoMateria(0)); //coloreo valido :	0
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		grafo.addMateria(new NodoMateria(3)); //coloreo valido :	3
		
		grafo.connectMateria(0, 1, 1 , 0);
		grafo.connectMateria(6, 13, 2 , 4);
		grafo.connectMateria(6, 30, 2 , 3);
		grafo.connectMateria(6, 27, 2 , 0);
		grafo.connectMateria(6, 17, 2 , 1);
		grafo.connectMateria(17, 27, 1 , 0);
		grafo.connectMateria(17, 29, 1 , 3);
		grafo.connectMateria(17, 12, 1 , 0);
		grafo.connectMateria(12, 29, 0 , 3);
		grafo.connectMateria(12, 4, 0 , 1);
		grafo.connectMateria(12,18 , 0 , 3);
		grafo.connectMateria(4, 25, 1 , 2);
		grafo.connectMateria(4, 18, 1 , 3);
		grafo.connectMateria(18,10 , 3 , 4);
		grafo.connectMateria(25,11 , 2 , 4);
		grafo.connectMateria(25, 8, 2 , 3);
		grafo.connectMateria(25, 9, 2 , 3);
		grafo.connectMateria(25, 24, 2 , 4);
		grafo.connectMateria(11,1 , 4 , 0);
		grafo.connectMateria(11, 19,4  , 0);
		grafo.connectMateria(11, 8, 4 , 3);
		grafo.connectMateria(19, 8, 0 , 3);
		grafo.connectMateria(19, 10, 0 , 4);
		grafo.connectMateria(8, 10, 3 , 4);
		grafo.connectMateria(10,1 , 4 , 0);
		grafo.connectMateria(1,7 , 0 , 1);
		grafo.connectMateria(1,9 , 0 , 3);
		grafo.connectMateria(9, 7, 3 , 1);
		grafo.connectMateria(7, 20, 1 , 4);
		grafo.connectMateria(7, 24, 1 , 4);
		grafo.connectMateria(9,20 , 3 , 4);
		grafo.connectMateria(20,22 , 4 , 1);
		grafo.connectMateria(20, 16, 4 , 0);
		grafo.connectMateria(16, 22, 0 , 1);
		grafo.connectMateria(16, 24, 0 , 4);
		grafo.connectMateria(16, 28, 0 , 3);
		grafo.connectMateria(28,20 , 3 , 4);
		grafo.connectMateria(28, 3, 3 , 2);
		grafo.connectMateria(3, 22, 2 , 1);
		grafo.connectMateria(3, 24, 2 , 4);
		grafo.connectMateria(24, 29, 4 , 3);
		grafo.connectMateria(3, 21, 2 , 3);
		grafo.connectMateria(28, 15, 3 , 2);
		grafo.connectMateria(21, 26, 3 , 4);
		grafo.connectMateria(15,29 , 2 , 3);
		grafo.connectMateria(15, 14, 2 , 0);
		grafo.connectMateria(26, 29, 4 , 3);
		grafo.connectMateria(26, 2, 4 , 2);
		grafo.connectMateria(26, 5, 4 , 2);
		grafo.connectMateria(5, 14, 2 , 0);
		grafo.connectMateria(5, 23, 2 , 1);
		grafo.connectMateria(23,2 , 1 , 2);
		grafo.connectMateria(2, 14, 2 , 0);
		grafo.connectMateria(2, 30, 2 , 3);
		grafo.connectMateria(2, 13, 2 , 4);
		grafo.connectMateria(13,30 , 4 , 3);
		grafo.connectMateria(13, 27, 4 , 0);
		grafo.connectMateria(27,30 , 0 , 3);
		return grafo;
	}
}
