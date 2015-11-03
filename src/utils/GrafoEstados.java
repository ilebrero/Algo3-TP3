package utils;

import java.util.ArrayList;
import java.util.List;

//Grafo sobre Lista de Adyacencia

public class GrafoEstados extends GrafoMaterias{
	private final int OFFSET_NEGADO = 2;

	private ArrayList<NodoEstado> grafoEstados;
	private ArrayList<Conexion>   conexiones;

	public GrafoEstados() {
		conexiones = new ArrayList<Conexion>();
	}

	public void connectMateria(int m1, int m2) {
		Materia  materia1, materia2;
		Conexion c;

		materia1 = grafoMateria.get(m1);
		materia2 = grafoMateria.get(m2);
		
		materia1.addAdyacente(materia2);
		materia2.addAdyacente(materia1);

		c = new Conexion(m1, m2);
		conexiones.add(c);
	}

	public int size() {
		return grafoEstados.size();
	}
	
	public ArrayList<NodoEstado> getNodosEstado() {
		return this.grafoEstados;
	}
	
	public NodoEstado getNodoEstado(int i) {
		return grafoEstados.get(i);
	}
	
	public void generarGrafoDeEstados() {
		grafoEstados = new ArrayList<NodoEstado>();
	
		for (Materia m : grafoMateria) {
			generarNodosEstado(m);
		}

		for (Conexion c : conexiones) {
			conectarEstados(c);
		}
	}

	public void generarNodosEstado(Materia m) {
		ArrayList<NodoEstado> estadosActuales = new ArrayList<NodoEstado>();
		int id, idPadre, c1, c2;
		
		idPadre = m.getId();
		id = grafoEstados.size();
		c1 = m.getColor(0);
		c2 = m.getColor(1);
		
		estadosActuales.add( new NodoEstado(id	  , idPadre, c1, false) );
		estadosActuales.add( new NodoEstado(id + 1, idPadre, c2, false) );
		estadosActuales.add( new NodoEstado(id + 2, idPadre, c1, true ) );
		estadosActuales.add( new NodoEstado(id + 3, idPadre, c2, true ) );

		grafoEstados.addAll( estadosActuales );
		m.addEstados( estadosActuales );
	}

	public void conectarEstados (Conexion c) {
		Materia n1 = grafoMateria.get(c.getM1());
		Materia n2 = grafoMateria.get(c.getM2());

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				if (n1.getColor(i) == n2.getColor(j)) {
					n1.getEstado(i).connect(n2.getEstado(j + OFFSET_NEGADO));
					n2.getEstado(j).connect(n1.getEstado(i + OFFSET_NEGADO));
				} else {
					n1.getEstado(i).connect(n2.getEstado(j));
					n2.getEstado(j).connect(n1.getEstado(i));
				}
			}
		}
	}
	
	/*
	 * TODO: revizar el tema del grafo invertido, todavia no hace nada
	 */	
	public static GrafoEstados grafoInvertido(GrafoEstados g) {
		GrafoEstados invertido = new GrafoEstados();
		
		List<Materia> nodos = g.getMaterias();
		for (Materia m : nodos) {
			invertido.addMateria(m);
		}
		
		return invertido;
	}
}

