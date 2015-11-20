package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import ejercicio3.Ejercicio3;
import utils.GrafoPredicados;
import utils.NodoMateria;
import utils.Tuple;

public class TestEj5 {

//	@Test
	public void testEstrella() {
		for (int i = 0; i < 100000000; i++) {
			Math.random();
		}
		
		for (int i = 0; i < 1000; i++) {
			double tiempoFinal = 0;
			Ejercicio3 ej;
			GrafoPredicados grafo = generateStar(i, i, 100);
			int conflictos1 = Integer.MAX_VALUE;
			for (int j = 0; j < 3; j++) {
				ej  = new Ejercicio3(grafo);
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreo());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
			}
			System.out.println(tiempoFinal + ";" + conflictos1);
		}
	}
	@Test
	public void testGrafo() {
		for (int i = 0; i < 100000000; i++) {
			Math.random();
		}
		
		GrafoPredicados grafo = generateStar(10, 10, 10);
		ArrayList<Tuple<Integer,Integer>> aristas = generateAristas(10, 10);
		System.out.println(aristas.size());
		int cantAristas = aristas.size();
		for (int i = 0; i < cantAristas; i++) {
			for (int j = 0; j < 1; j++) {
				Tuple<Integer, Integer> tuple = aristas.get(0);
				aristas.remove(0);
				grafo.connectMateria(tuple.getX(), tuple.getY());
			}
			
			
			double tiempoFinal = 0;
			Ejercicio3 ej;	
			int conflictos1 = Integer.MAX_VALUE;
			for (int j = 0; j < 3; j++) {
				ej  = new Ejercicio3(grafo);
				double tiempo = System.nanoTime();
				conflictos1 = Math.min(conflictos1, ej.checkColoreo());
				tiempoFinal += (System.nanoTime() - tiempo)/1000;
			}
			System.out.println(tiempoFinal + ";" + conflictos1);
		}
	}
	
	private GrafoPredicados generateStar(int cantidad, int longEstrella, int colores){
		GrafoPredicados grafo = new GrafoPredicados();
		 grafo.addMateria(new NodoMateria(colores, true));
		   for (int j = 0; j < cantidad; j++) {
			  for (int i = 0; i < longEstrella; i++) {
				  grafo.addMateria(new NodoMateria(colores, true));
			  }
			  grafo.connectMateria(0, j * longEstrella + 1);
			  for (int i = 0; i < longEstrella - 1; i++) {
				  grafo.connectMateria((j* longEstrella) +i, (j* longEstrella)+i+1);
			  }
			  
		   }
		   
		return grafo;
	}
	private ArrayList<Tuple<Integer,Integer>> generateAristas(int cantidad, int longEstrella){
			ArrayList<Tuple<Integer,Integer>> aristas = new ArrayList<Tuple<Integer,Integer>>();
//			for (int j = 0; j < cantidad; j++) {
//				   for (int k = 1; k < longEstrella; k++) {
//						 aristas.add(new Tuple<Integer, Integer>(0,j + k));
//				   }
//			}
			
			int[][] matriz = new int[cantidad + 1][longEstrella + 1];
			
			
			for (int j = 0; j < cantidad * longEstrella; j++) {
				for (int k = 0; k < cantidad * longEstrella; k++) {
						if (j != k && Math.abs(j- k ) != 1 && ! aristas.contains(new Tuple<Integer, Integer>(j, k))){
						 aristas.add(new Tuple<Integer, Integer>(j, k));
				
						 
						}
				   }
			}
			
			
			
			Collections.shuffle(aristas);
			
			return aristas;
			
		}
		
	}

