package utils;

import java.util.ArrayList;

//Grafo sobre Lista de Adyacencia

public class GrafoEstados extends GrafoMaterias{
	protected final int OFFSET_NEGADO = 2;

	protected final int OFFSET_COLOR1 = 0;
	protected final int OFFSET_COLOR2 = 1;
	protected final int OFFSET_COLOR1_NEGADO = 2;
	protected final int OFFSET_COLOR2_NEGADO = 3;

	protected ArrayList<NodoEstado> grafoEstados;
	protected ArrayList<Conexion>   conexiones;

	public GrafoEstados() {
		conexiones = new ArrayList<Conexion>();
	}

	public void connectMateria(int m1, int m2) {
		NodoMateria  materia1, materia2;
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
	
		for (NodoMateria m : grafoMateria) {
			generarNodosEstado(m);
		}

		for (Conexion c : conexiones) {
			conectarEstados(c);
		}
	}

	public void generarNodosEstado(NodoMateria m) {
		ArrayList<NodoEstado> estadosActuales;
		int id, idPadre, c1, c2;
		
		idPadre = m.getId();
		id = grafoEstados.size();
		c1 = m.getColor(0);
		c2 = m.getColor(1);
		
		estadosActuales = crearNodos(id, idPadre, c1, c2);
		conectarEstadosInternos( estadosActuales );

		grafoEstados.addAll( estadosActuales );
		m.addEstados( estadosActuales );
	}

	private ArrayList<NodoEstado> crearNodos(int id, int idPadre, int c1, int c2) {
		ArrayList<NodoEstado> estadosActuales = new ArrayList<NodoEstado>();

		estadosActuales.add( new NodoEstado(id + OFFSET_COLOR1		 , idPadre, c1, false) );
		estadosActuales.add( new NodoEstado(id + OFFSET_COLOR2		 , idPadre, c2, false) );
		estadosActuales.add( new NodoEstado(id + OFFSET_COLOR1_NEGADO, idPadre, c1, true ) );
		estadosActuales.add( new NodoEstado(id + OFFSET_COLOR2_NEGADO, idPadre, c2, true ) );

		return estadosActuales;
	}

	private void conectarEstadosInternos(ArrayList<NodoEstado> estados) {
		estados.get(OFFSET_COLOR1).connect(estados.get(OFFSET_COLOR2_NEGADO));
		estados.get(OFFSET_COLOR2_NEGADO).connect(estados.get(OFFSET_COLOR1));

		estados.get(OFFSET_COLOR2).connect(estados.get(OFFSET_COLOR1_NEGADO));
		estados.get(OFFSET_COLOR1_NEGADO).connect(estados.get(OFFSET_COLOR2));
	}

	public void conectarEstados (Conexion c) {
		NodoMateria n1 = grafoMateria.get(c.getM1());
		NodoMateria n2 = grafoMateria.get(c.getM2());

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				if (n1.getColor(i) == n2.getColor(j)) {
					n1.getEstado(i).connect(n2.getEstado(j + OFFSET_NEGADO));
					n2.getEstado(j).connect(n1.getEstado(i + OFFSET_NEGADO));
				} 
			}
		}
	}
	
	public ArrayList<NodoEstado> grafoInvertido() {
		ArrayList<NodoEstado> invertido = copiarNodosVacios();

		for (NodoEstado actual : grafoEstados) {
			NodoEstado nuevoIn = invertido.get( actual.getId() );
			
			for (NodoEstado vecino : actual.getAdyacentes()) {
				NodoEstado nuevoOut = invertido.get( vecino.getId() );
				nuevoOut.connect(nuevoIn);
			}
		}
		
		return invertido;
	}

	private ArrayList<NodoEstado> copiarNodosVacios() {
		ArrayList<NodoEstado> resultado = new ArrayList<NodoEstado>();
		
		for (NodoEstado e : grafoEstados) {
			NodoEstado copiaSinVecinos = new NodoEstado(e.getId(), e.getPadreId(), e.getColor(), e.getNegado()); 
			resultado.add(copiaSinVecinos);
		}

		return resultado;
	}
}