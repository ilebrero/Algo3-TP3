package ejercicio3;

import utils.GrafoMaterias;
import utils.NodoMateria;

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
		for (NodoMateria materia : grafo.getMaterias()) {
			if (materia.getColores().size() == 1){
				coloreo[materia.getId()] = materia.getColores().get(0);
			} else {
				boolean seteeColor = false;
				int i; int color;
				for(i = 0 ; i < materia.getColores().size() && !seteeColor ;i++){
					boolean colorValido = true;
					color =  materia.getColores().get(i);
					for (NodoMateria vecino :materia.getAdyacentes()){
						if(vecino.getColores().size() == 1 && vecino.getColores().contains(color)){
							colorValido = false;
						}
					}
					if (colorValido){
						seteeColor = true;
						coloreo[materia.getId()] = color;
					}
				}
				if (! seteeColor){
					// El goloso se encuentra con un conflicto.
					// Seteo el ultimo color.
					coloreo[materia.getId()] = color;
				}
			}
		}
		return 0;
	}
	
	public int solve2(){
		for (NodoMateria materia : grafo.getMaterias()) {
			if (materia.getColores().size() == 1){
				coloreo[materia.getId()] = materia.getColores().get(0);
			} else {
				boolean seteeColor = false; int colorFactible;
				int i; int color; int posibilidades;int mayorPosibilidad = 0;
				for(i = 0 ; i < materia.getColores().size() ;i++){
					posibilidades = 0;
					boolean colorValido = true;
					color =  materia.getColores().get(i);
					for (NodoMateria vecino :materia.getAdyacentes()){
						if(vecino.getColores().size() == 1 && vecino.getColores().contains(color)){
							colorValido = false;
						}
						
						if (! vecino.getColores().contains(color)){
							posibilidades += vecino.getColores().size();
						} else {
							posibilidades += vecino.getColores().size() - 1;
						}
					}
					if (colorValido && posibilidades > mayorPosibilidad){
						mayorPosibilidad = posibilidades;
						coloreo[materia.getId()] = color;
						seteeColor = true;
					}
					if (posibilidades > mayorPosibilidad){
						colorFactible = color;
					}
				}
				if (! seteeColor){
					// El goloso se encuentra con un conflicto.
					// Seteo el ultimo color.
					coloreo[materia.getId()] = colorFactible;
				}
			}
		}
		return 0;
	}
}
// O (cantMaterias) * O (mayorVecindad) * ((NodoConMascolores) ^ 2) 