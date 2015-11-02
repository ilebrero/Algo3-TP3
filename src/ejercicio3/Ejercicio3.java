package ejercicio3;

import utils.Materia;
import utils.Nodo;

public class Ejercicio3 {
	private GrafoMaterias grafo;
	private int[] coloreo;
	public Ejercicio3() {
		grafo = new GrafoMaterias();
		coloreo = new int[grafo.size()];
		for (int i = 0; i < coloreo.length; i++) {
			coloreo[i] = -1;
		}
	}
	
	public int solve(){
		for (Materia materia : grafo.getNodos()) {
			if (materia.getColores().getSize() == 1){
				coloreo[materia.getId()] = materia.GetColores().get(0);
			} else {
				boolean seteeColor = false;
				int i; int color;
				for(i = 0 ; i < materia.getColores() && !seteeColor ;i++){
					boolean mismoColor = false;
					color =  materia.getColores().get(i);
					for (Materia vecino :materia.getAdyacentes()){
						if(vecino.getColores().contains(color)){
							mismoColor = true;
						}
					}
					if (!mismoColor){
						seteeColor = true;
						coloreo[materia.getId()] = color;
					}
				}
				if (! seteeColor){
					// El goloso se encuentra con un comflicto.
					// Seteo el ultimo color.
					coloreo[materia.getId()] = color;
				}
			}
		}
		return 0;
	}
}
// O (cantMaterias) * O (mayorVecindad) * ((NodoConMascolores) ^ 2) 