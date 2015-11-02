package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import utils.GrafoMaterias;
import utils.Materia;

//Grafo sobre Lista de Adyacencia

public class GrafoEstados extends GrafoMaterias{
	
	private class Estados{
		public int materiaId;
		public NodoEstado[] estados;
		
		public Estados(int id, int c1, int c2) {
			materiaId = id;
			estados   = new NodoEstado[4];
			
			for (int i = 0 ; i < 4; ++i) {
				estados[i] = new NodoEstado();
			}

			estados[0].setColor(c1);
			estados[1].setColor(c2);
			estados[2].setColor(c1);
			estados[3].setColor(c2);

			estados[2].setNegado(true);
			estados[3].setNegado(true);	
		}
	}
	
	private ArrayList<Materia> grafoMateria;
	private ArrayList<NodoEstado> grafoEstados;
	private ArrayList<----> conexiones;
	
	public GrafoEstados(){
		grafoMateria = new ArrayList<Materia>();
		conexiones   = new ArrayList<>();
	}
	
	public generarGrafoEstados() {
		grafoEstados = new ArrayList<NodoEstado>();
	
		//crear estados
		Estados e = new Estados

		//agregar al grafoestados
	}

	public void addMateria(Materia m) {
		int id = grafoMateria.size();
		m.setId(id);
		
		grafoMateria.add(m);
	}
	
	/*public void connectMateria(int m1, int m2) {
		
		//agregarlos como adyacentes
		Materia a, b;
		a = grafoMateria.get(m1);
		b = grafoMateria.get(m2);
		
		a.addAdyacente(b);
		b.addAdyacente(a);

		Conexion c = new Conexion(m1, m2);
		conexiones.add(c);
	}

	public void connect(Materia m2) {
		if (m2.getC1() == c1){
			nodos[0].connect(m2.getNodoNegadoC1());
		} else {
			nodos[0].connect(m2.getNodoC1());
		}
		if (m2.getC2() == c2){
			nodos[1].connect(m2.getNodoNegadoC2());
		} else {
			nodos[1].connect(m2.getNodoC2());
		}
	}

	public void generarNodos() {
		nodos[0].setColor(c1);
		nodos[1].setColor(c2);
		nodosNegados[0].setColor(c1);
		nodosNegados[0].setNegado(true);
		nodosNegados[1].setColor(c2);
		nodosNegados[1].setNegado(true);
	}
	
	public void connect(Materia m2) {
		if (m2.getC1() == c1){
			nodos[0].connect(m2.getNodoNegadoC1());
		} else {
			nodos[0].connect(m2.getNodoC1());
		}
		if (m2.getC2() == c2){
			nodos[1].connect(m2.getNodoNegadoC2());
		} else {
			nodos[1].connect(m2.getNodoC2());
		}
	}*/
	


	/*
	 * TODO: revizar el tema del grafo invertido
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

