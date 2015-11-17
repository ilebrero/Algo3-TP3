package Test;

import java.util.ArrayList;
import java.util.List;

import utils.GrafoPredicados;
import utils.NodoMateria;

public class Generador{

	public Generador() {}
	
	//int probabilidad esta entre 0 y 100
	public  void generarConexiones(GrafoPredicados g, int cantidadConexiones, int probabilidad) {
		List<NodoMateria> nodos = g.getMaterias();
		boolean [][] conectados = new boolean [nodos.size()][nodos.size()];

		while (cantidadConexiones > 0) {
			for (NodoMateria n1 : nodos) {
				for (NodoMateria n2 : nodos) {
					if (n1.getId() != n2.getId() && !conectados[n1.getId()][n2.getId()] && !conectados[n2.getId()][n1.getId()]) {
						int random = (int) (Math.random() * 100);
						
						if (random <= probabilidad) {
							conectados[n1.getId()][n2.getId()] = true;
							conectados[n2.getId()][n1.getId()] = true;
							
							g.connectMateria(n1.getId(), n2.getId());
							
							cantidadConexiones--;
						}
					}
				}
			}
		}
		
	}
	
	public static ArrayList<NodoMateria> generarNodosConColoresPosibles(int colorMax, int cantidadNodos, int distancia) {
		ArrayList<NodoMateria> nodos = new ArrayList<NodoMateria>();
		
		for (int i = 0; i < cantidadNodos; ++i) {
			ArrayList<Integer> colores = new ArrayList<Integer>();
			int cantColores = (int) (Math.random() * (colorMax - 1)) + 1;
			int inicio      = (int) (Math.random() * (cantColores - 1)) + 1;
			
			if (cantColores - inicio > distancia) cantColores -= distancia;
			
			for (int j = inicio; j < cantColores + 1; ++j) {
				colores.add(j);
			}
			
			NodoMateria nuevo = new NodoMateria(colores);
			
			nodos.add(nuevo);
		}
		
		return nodos;
	}
	
	public static ArrayList<NodoMateria> generarNodosConColores(int colorMax, int cantidadNodos, int distancia) {
		ArrayList<NodoMateria> nodos = new ArrayList<NodoMateria>();
		
		for (int i = 0; i < cantidadNodos; ++i) {
			ArrayList<Integer> colores = new ArrayList<Integer>();
			int cantColores = (int) (Math.random() * (colorMax-1)) + 1;
			int inicio      = (int) (Math.random() * (cantColores-1)) + 1;
			
			if (cantColores - inicio > distancia) cantColores -= distancia;
			
			for (int j = inicio; j <= cantColores + 1; ++j) {
				colores.add(j);
			}
			
			NodoMateria nuevo = new NodoMateria(colores);

			for (Integer color : colores) {
				nuevo.setColor(color);
			}
			
			nodos.add(nuevo);
		}
		
		return nodos;
	}
}
