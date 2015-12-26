package utils;

import java.util.ArrayList;

//Grafo sobre Lista de Adyacencia

public class GrafoPredicados extends GrafoMaterias{
	protected final int OFFSET_COLOR1 = 0;
	protected final int OFFSET_COLOR2 = 1;
	protected final int OFFSET_COLOR1_NEGADO = 2;
	protected final int OFFSET_COLOR2_NEGADO = 3;

	protected ArrayList<NodoPredicado> grafoEstados;
	protected ArrayList<Conexion>      conexiones;

	public GrafoPredicados() {
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

	public int sizeEstados() {
		return grafoEstados.size();
	}
	
	public ArrayList<NodoPredicado> getNodosEstado() {
		return this.grafoEstados;
	}
	
	public NodoPredicado getNodoEstado(int i) {
		return grafoEstados.get(i);
	}
	
	public void generarGrafoDeEstados() {
		grafoEstados = new ArrayList<NodoPredicado>();
		
		for (NodoMateria m : grafoMateria) {
			generarNodosEstado(m);
		}

		for (Conexion c : conexiones) {
			conectarEstados(c);
		}
	}

	public void generarNodosEstado(NodoMateria m) {
		if (m.getColores().size() > 0) {
			ArrayList<NodoPredicado> estadosActuales;
			int id, idPadre, color[], cantidadColores;
			
			id 		= grafoEstados.size();
			color 	= new int[2];
			idPadre = m.getId();
			cantidadColores = m.getColores().size();
			
			for(int i = 0; i < cantidadColores; ++i) {
				color[i] = m.getColor(i);
			}
			
			estadosActuales = crearNodos(id, idPadre, color, cantidadColores);
			conectarEstadosInternos( estadosActuales );
	
			grafoEstados.addAll( estadosActuales );
			m.addEstados( estadosActuales );
		}
	}

	private ArrayList<NodoPredicado> crearNodos(int id, int idPadre, int [] color, int cantidad) {
		ArrayList<NodoPredicado> estadosActuales = new ArrayList<NodoPredicado>();
		
		switch (cantidad) {
		case (2) :
			estadosActuales.add( new NodoPredicado(id + OFFSET_COLOR1		, idPadre, color[0], false) );
			estadosActuales.add( new NodoPredicado(id + OFFSET_COLOR2		, idPadre, color[1], false) );
			estadosActuales.add( new NodoPredicado(id + OFFSET_COLOR1_NEGADO, idPadre, color[0], true ) );
			estadosActuales.add( new NodoPredicado(id + OFFSET_COLOR2_NEGADO, idPadre, color[1], true ) );
			break;
		case (1) : //esto quedo medio harcodeado
			estadosActuales.add( new NodoPredicado(id + OFFSET_COLOR1, idPadre, color[0], false) );
			estadosActuales.add( new NodoPredicado(id + OFFSET_COLOR2, idPadre, color[0], true ) );
		}
		
		return estadosActuales;
	}

	private void conectarEstadosInternos(ArrayList<NodoPredicado> estados) {
		if (estados.size() == 4) {
			estados.get(OFFSET_COLOR1).connect(estados.get(OFFSET_COLOR2_NEGADO));
			estados.get(OFFSET_COLOR2_NEGADO).connect(estados.get(OFFSET_COLOR1));
	
			estados.get(OFFSET_COLOR2).connect(estados.get(OFFSET_COLOR1_NEGADO));
			estados.get(OFFSET_COLOR1_NEGADO).connect(estados.get(OFFSET_COLOR2));
		} else if (estados.size() == 2) {
			estados.get(OFFSET_COLOR2).connect(estados.get(OFFSET_COLOR1));
		}
	}

	public void conectarEstados (Conexion c) {
		NodoMateria n1 = grafoMateria.get(c.getM1());
		NodoMateria n2 = grafoMateria.get(c.getM2());
		int cantN1 = n1.getColores().size();
		int cantN2 = n2.getColores().size();
		
		for (int i = 0; i < cantN1; ++i) {
			for (int j = 0; j < cantN2; ++j) {
				if (n1.getColor(i) == n2.getColor(j)) {
					n1.getEstado(i).connect(n2.getEstado(j + cantN2));
					n2.getEstado(j).connect(n1.getEstado(i + cantN1));
				} 
			}
		}
	}
	
	public ArrayList<NodoPredicado> grafoInvertido() {
		ArrayList<NodoPredicado> invertido = copiarNodosVacios();

		for (NodoPredicado actual : grafoEstados) {
			NodoPredicado nuevoIn = invertido.get( actual.getId() );
			
			for (NodoPredicado vecino : actual.getAdyacentes()) {
				NodoPredicado nuevoOut = invertido.get( vecino.getId() );
				nuevoOut.connect(nuevoIn);
			}
		}
		
		return invertido;
	}

	private ArrayList<NodoPredicado> copiarNodosVacios() {
		ArrayList<NodoPredicado> resultado = new ArrayList<NodoPredicado>();
		
		for (NodoPredicado e : grafoEstados) {
			NodoPredicado copiaSinVecinos = new NodoPredicado(e.getId(), e.getPadreId(), e.getColor(), e.getNegado()); 
			resultado.add(copiaSinVecinos);
		}

		return resultado;
	}

	
}