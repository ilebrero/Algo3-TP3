package Test;

import java.util.ArrayList;
import utils.GrafoPredicados;
import utils.NodoMateria;

public class Generador{

	public static void generarConexiones(GrafoPredicados g, int cantidadConexiones) {
		ArrayList<NodoMateria> nodos = (ArrayList<NodoMateria>) g.getMaterias();
		boolean [][] conectados = new boolean [nodos.size()][nodos.size()];
		
		while (cantidadConexiones > 0) {
			int n1, n2;
			
			n1 = (int) (Math.random() * nodos.size());
			n2 = (int) (Math.random() * nodos.size());
			
			if (n1 != n2) {
				if (!conectados[n1][n2]) {
					conectados[n1][n2] = true;
					conectados[n2][n1] = true;
					
					g.connectMateria(n1, n1);
					cantidadConexiones--;
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
