package exercises3;


import java.util.ArrayList;

public class Materia {
	private int id;
	private ArrayList<Materia> adyacentes;
	private Nodo[] nodos;
	private Nodo[] nodosNegados;
	private int c1;
	private int c2;
			
	public Materia(int id, int c1, int c2) {
		this.id = id;
		this.c1 = c1;
		this.c2 = c2;
		nodos = new Nodo[2];
		nodosNegados = new Nodo[2];
		for (int i = 0; i < nodos.length; i++) {
			nodos[i] = new Nodo();
			nodosNegados[i] = new Nodo();
		}
		adyacentes = new ArrayList<Materia>();
		generarNodos();
	}
	
	public void addMateria(Materia adyacente) {
		adyacentes.add(adyacente);
		this.connect(adyacente);
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
	}
	public int getC1() {
		return c1;
	}
	public int getC2() {
		return c2;
	}
	public Nodo getNodoC1() {
		return nodos[0];
	}
	public Nodo getNodoC2() {
		return nodos[1];
	}
	public Nodo getNodoNegadoC1() {
		return nodos[0];
	}
	public Nodo getNodoNegadoC2() {
		return nodos[1];
	}
	public int getId() {
		return id;
	}
	
	
}