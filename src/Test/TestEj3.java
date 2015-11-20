package Test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import ejercicio3.Ejercicio3;
import utils.GrafoPredicados;
import utils.GrafoMaterias;
import utils.NodoMateria;
import utils.Serializar;
import utils.Tuple;


public class TestEj3 {
//	private GrafoMaterias grafo =  crearGrafo();
	private GrafoMaterias grafo;
//	private GrafoMaterias grafoRompe2 =  crearGrafoRompe2();
	private GrafoMaterias grafoRompe2;
	private GrafoMaterias grafo2Test;
//	private GrafoMaterias kn = generarKn(30000);
//	private GrafoMaterias kn;
	public TestEj3(){
		
	}
	@Test
	public void test4(){
		for(int i = 1 ; i <= 500; i += 10){
			Ejercicio3 ej  = new Ejercicio3(generarKn(10));
		}
		
	for(int i = 1 ; i <= 500; i++){
//		grafo = generarKnNuevo(i);
		grafo = generarKn(i);
		double tiempoFinal = 0;
		Ejercicio3 ej;
		int conflictos1 = Integer.MAX_VALUE;
		for (int j = 0; j < 3; j++) {
			ej  = new Ejercicio3(grafo);
			double tiempo = System.nanoTime();
			conflictos1 = Math.min(conflictos1, ej.checkColoreo());
			tiempoFinal += (System.nanoTime() - tiempo)/1000;
		}
		System.out.print(Math.round((tiempoFinal/ 3))+";"+conflictos1+";");
		tiempoFinal = 0;
		conflictos1 = Integer.MAX_VALUE;
		for (int j = 0; j < 3; j++) {
			ej  = new Ejercicio3(grafo);
			double tiempo = System.nanoTime();
			conflictos1 = Math.min(conflictos1, ej.checkColoreoV2());
			tiempoFinal += (System.nanoTime() - tiempo)/1000;
		}
		System.out.print(Math.round((tiempoFinal/ 3)) +";"+conflictos1+";");
		
//		tiempoFinal = 0;
//		conflictos1 = i;
//		for (int j = 0; j < 3; j++) {
//			ej  = new Ejercicio3(grafo);
//			double tiempo = System.nanoTime();
//			conflictos1 = Math.min(conflictos1, ej.checkColoreoV3());
//			tiempoFinal += (System.nanoTime() - tiempo)/1000;
//		}
//		System.out.print(Math.round((tiempoFinal/ 3)) +";"+conflictos1+";");
		System.out.println();
	}
}
//		@Test
	public void test() throws ClassNotFoundException {
//		try
//	      {
//	         FileOutputStream fileOut =
//	         new FileOutputStream(System.getProperty("user.dir") + "/src/grafos/ej3_grafoKn.ser");
//	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
//	         out.writeObject(kn);
//	         out.close();
//	         fileOut.close();
//
//	      }catch(IOException i)
//	      {
//	          i.printStackTrace();
//	      }
//	  
//			
			
			for(int i = 1 ; i <= 500; i += 10){
				Ejercicio3 ej  = new Ejercicio3(generarKn(10));
			}
			grafo = generarCiclo(100);
			ArrayList<Tuple<Integer,Integer>> aristas = aristasCiclo(100);
			System.out.println("Hay " + aristas.size() + "aristas");
			int cantAristas = aristas.size();
		for(int i = 1 ; i <= cantAristas; i++){
			if (aristas.size() > 0){
				Tuple<Integer, Integer> tuple = aristas.get(0);
//				System.out.println("Conecto " + tuple.getX() + "Con " + tuple.getY());
				aristas.remove(0);
				grafo.connectMateria(tuple.getX(), tuple.getY());
			} else {
				System.out.println("No hay mas");
			}
			double tiempoFinal = 0;
			Ejercicio3 ej;
			int conflictos1 = i;
			for (int j = 0; j < 3; j++) {
				ej  = new Ejercicio3(grafo);
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreo());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
			}
			System.out.print(Math.round((tiempoFinal/ 3))+";"+conflictos1+";");
			tiempoFinal = 0;
			conflictos1 = i;
			for (int j = 0; j < 3; j++) {
				ej  = new Ejercicio3(grafo);
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreoV2());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
			}
			System.out.print(Math.round((tiempoFinal/ 3)) +";"+conflictos1+";");
			tiempoFinal = 0;
			conflictos1 = i;
			for (int j = 0; j < 3; j++) {
				ej  = new Ejercicio3(grafo);
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreoV3());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
			}
			System.out.print(Math.round((tiempoFinal/ 3)) +";"+conflictos1+";");
			System.out.println();
			
		}
			
			
		
	}
	@Test
	public void test2() throws ClassNotFoundException {
		try
	      {
	         FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/grafos/ej3_grafo.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         grafo = (GrafoMaterias) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }
		try
	      {
	         FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/grafos/ej3_grafo2Test.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         grafo2Test = (GrafoMaterias) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }
		try
	      {
	         FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/grafos/ej3_grafoRompe2.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         grafoRompe2 = (GrafoMaterias) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }
		
		
		
		System.out.println("Conflictos con grafo 1:" + new Ejercicio3(grafo).checkColoreo()); 
		System.out.println("Conflictos con grafo 2:" + new Ejercicio3(grafoRompe2).checkColoreo()); 
		System.out.println("Conflictos con grafo 3 :" + new Ejercicio3(this.grafoTest2()).checkColoreo()); 
		System.out.println("Conflictos con grafo k200 :" + new Ejercicio3(generarKn(100)).checkColoreo()); 
		System.out.println("Conflictos con grafo 1 :" + new Ejercicio3(grafo).checkColoreoV2()); 
		System.out.println("Conflictos con grafo 2 :" + new Ejercicio3(grafoRompe2).checkColoreoV2()); 
		System.out.println("Conflictos con grafo 3 :" + new Ejercicio3(this.grafoTest2()).checkColoreoV2()); 
		System.out.println("Conflictos con grafo k200 :" + new Ejercicio3(generarKn(100)).checkColoreoV2());
	}


private GrafoMaterias grafoTest2(){
	GrafoMaterias grafo = new GrafoMaterias();
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
	
private GrafoMaterias crearGrafo(){
	GrafoMaterias grafo = new GrafoMaterias();
	
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

private GrafoMaterias crearGrafoRompe2(){
	GrafoMaterias grafo = new GrafoMaterias();
	
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

private  GrafoMaterias generarKnNuevo(int i){
	Random randomGenerator = new Random();
	
	 int aristas = 0;
	   GrafoMaterias grafo = new GrafoMaterias();
	   for (int j = 0; j < i; j++) {
		  grafo.addMateria(new NodoMateria(i, true));
	   }
	  grafo.addMateria(new NodoMateria(1, true));
	   for (int j = 0; j < i; j++) {
		   int p = randomGenerator.nextInt(j + 1);
		   int o = randomGenerator.nextInt(j + 1);
		   for (int k = Math.min(p, o); k < Math.max(p, o); k++) {
			   if (j != k){
//			   System.out.println("conecto " + j + "con "+ k);
				  grafo.connectMateria(j, k);
				  aristas++;
			   }
		   }
	   }
//	   System.out.println("Cantidad de aristas conectadas" + aristas);
	   return grafo;
}

private  GrafoPredicados generarKn(int i){
	Random randomGenerator = new Random();
	
	 int aristas = 0;
	 GrafoPredicados grafo = new GrafoPredicados();
	   for (int j = 0; j < i; j++) {
		  grafo.addMateria(new NodoMateria(i, true));
	   }
	  grafo.addMateria(new NodoMateria(1, true));
	  for (int j = 0; j < i; j++) {
		   for (int k = randomGenerator.nextInt(j + 1); k < randomGenerator.nextInt(j + 1); k++) {
			   if (j != k){
//			   System.out.println("conecto " + j + "con "+ k);
				  grafo.connectMateria(j, k);
				  aristas++;
			   }
		   }
	   }
//	   System.out.println("Cantidad de aristas conectadas" + aristas);
	   return grafo;
 }

	private  GrafoMaterias generarKn1(int i){
		Random randomGenerator = new Random();
		
		 int aristas = 0;
		   GrafoMaterias grafo = new GrafoMaterias();
		   for (int j = 0; j < i; j++) {
			  grafo.addMateria(new NodoMateria(i, true));
		   }
		  grafo.addMateria(new NodoMateria(1, true));
		   for (int j = 0; j < i; j++) {
			   for (int k = 0; k < j; k++) {
				   if (j != k){
	//			   System.out.println("conecto " + j + "con "+ k);
					  grafo.connectMateria(j, k);
					  aristas++;
				   }
			   }
		   }
	//	   System.out.println("Cantidad de aristas conectadas" + aristas);
		   return grafo;
	}
	private  GrafoMaterias generarCiclo(int i){
		 int aristas = 0;
		   GrafoMaterias grafo = new GrafoMaterias();
		   for (int j = 0; j <= i; j++) {
			  grafo.addMateria(new NodoMateria(0, true));
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
	
	//@Test
	public void testCrecimientoNodosSinConexiones() {
		for (int i = 1; i < 20000; ++i) {
			double tiempoFinal = 0;
			Ejercicio3 ej;
			int conflictos1 = i;
			
			for (int j = 0; j < 3; j++) {
				GrafoPredicados grafo = new GrafoPredicados();
				int cantidadNodos = (int) (/*Math.random() * 100 **/ i);
				int colores = (int) (/*Math.random() * 100 **/ i);
				int cantidadColores = (int) (/*Math.random() * 100 **/ i);
				int cantidadConexiones = (int) (/*Math.random() * 300 **/ i);
				
				ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(colores, cantidadNodos, cantidadColores);
				for (NodoMateria n : nodos) {
					grafo.addMateria(n);
				}
			
				ej  = new Ejercicio3(grafo);
				tiempoFinal = 0;
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreo());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
				
				System.out.println(tiempoFinal);
			}
			
		}
	}
	
//	@Test
	public void testCrecimientoNodosConConexiones() {
		for (int i = 1; i < 2000; ++i) {
			double tiempoFinal = 0;
			Ejercicio3 ej;
			int conflictos1 = i;
			
			for (int j = 0; j < 3; j++) {
				GrafoPredicados grafo = new GrafoPredicados();
				int colores = (int) (/*Math.random() * 100 **/ i);
				int cantidadNodos = (int) (/*Math.random() * 100 **/ i);
				int cantidadColores = (int) (/*Math.random() * 100 **/ i);
				int cantidadConexiones = (int) (/*Math.random() * 300 **/ i-1);

				ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(colores, cantidadNodos, cantidadColores);
				for (NodoMateria n : nodos) {
					grafo.addMateria(n);
				}
				
				Generador g = new Generador();
				
				g.generarConexiones(grafo, cantidadConexiones, 50);
				
				ej  = new Ejercicio3(grafo);
				tiempoFinal = 0;
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreo());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
				
				System.out.println(tiempoFinal);
			}
			
		}
	}
	
//	@Test
	public void testCrecimientoNodosConConexionesycoloresCrecientes() {
		for (int i = 1; i < 2000; ++i) {
			double tiempoFinal = 0;
			Ejercicio3 ej;
			int conflictos1 = i;
			
			for (int j = 0; j < 3; j++) {
				GrafoPredicados grafo = new GrafoPredicados();
				int cantidadNodos = (int) (/*Math.random() * 100 **/ i);
				int colores = (int) (/*Math.random() * 100 **/ i);
				int cantidadColores = (int) (/*Math.random() * 100 **/ i);
				int cantidadConexiones = (int) (/*Math.random() * 300 **/ i-1);

				ArrayList<NodoMateria> nodos = Generador.generarNodosConColores(colores, cantidadNodos, cantidadColores);
				for (NodoMateria n : nodos) {
					grafo.addMateria(n);
				}
				
				Generador g = new Generador();
				
				g.generarConexiones(grafo, cantidadConexiones, 50);
				
				ej  = new Ejercicio3(grafo);
				tiempoFinal = 0;
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreo());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
				
				System.out.println(tiempoFinal);
			}
			
		}
	}
}

