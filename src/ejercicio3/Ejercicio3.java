package ejercicio3;

import java.util.Collections;
import java.util.Random;

import utils.GrafoMaterias;
import utils.NodoMateria;

public class Ejercicio3 {
	private GrafoMaterias grafo;
	private int[] coloreo;
	private int conflictos;
	
	public Ejercicio3(GrafoMaterias grafo) {
		this.grafo = grafo;
		coloreo = new int[grafo.size()];
		conflictos = 0;
		
		for (int i = 0; i < coloreo.length; i++) {
			coloreo[i] = -1;
		}
	}
	
	public int[] getColoreo(){
		return this.coloreo;
	}
	
	public int checkColoreo(){
		this.solve();
		return grafo.findConflicts(coloreo);
	}
	
	public int checkColoreoV2() {
		this.solve2();
		return grafo.findConflicts(coloreo);
	}
	
	public int checkColoreoV3() {
		this.solve3();
		return grafo.findConflicts(coloreo);
	}
	
	public int checkColoreoFinal() {
		Random randomGenerator = new Random();
		int suma = 0;
		for (int i = 0; i < Math.round(grafo.getMaterias().size() * 0.2); i++) {
			if (grafo.getMateria(randomGenerator.nextInt(grafo.getMaterias().size())).getColores().size()
					< 20 ){
				this.solve2();
			}
		}
		this.solve3();
		
		return grafo.findConflicts(coloreo);
	}
	
	public int solve(){
		for (NodoMateria materia : grafo.getMaterias()) {
			if (materia.getColores().size() == 1){
				coloreo[materia.getId()] = materia.getColores().get(0);
			} else {
				boolean seteeColor = false;
				int i; int color = 0;
				for(i = 0 ; i < materia.getColores().size() && !seteeColor ;i++){
					boolean colorValido = true;
					color =  materia.getColores().get(i);
					for (NodoMateria vecino :materia.getAdyacentes()){
						if (coloreo[vecino.getId()] == -1){
							if(vecino.getColores().size() == 1 && vecino.getColores().contains(color)){
								colorValido = false;
							}
						} else {
							if (coloreo[vecino.getId()] == color){
								colorValido = false;
							}
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
					conflictos++;
				}
			}
		}
		return conflictos;
	}
	
	public int solve2(){
		for (NodoMateria materia : grafo.getMaterias()) {
			if (materia.getColores().size() == 1){
				coloreo[materia.getId()] = materia.getColores().get(0);
			} else {
				boolean seteeColor = false; int colorFactible = 0;
				int i; int color; int posibilidades;int mayorPosibilidad = -1;
				for(i = 0 ; i < materia.getColores().size() ;i++){
					posibilidades = 0;
					boolean colorValido = true;
					color =  materia.getColores().get(i);
					for (NodoMateria vecino :materia.getAdyacentes()){
						if (coloreo[vecino.getId()] == -1){
							if(vecino.getColores().size() == 1 && vecino.getColores().contains(color)){
								colorValido = false;
							}
							if (! vecino.getColores().contains(color)){
								posibilidades += vecino.getColores().size();
							} else {
								posibilidades += vecino.getColores().size() - 1;
							}
						} else {
							if (coloreo[vecino.getId()] == color){
								colorValido = false;
							}
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
					conflictos++;
				}
			}
		}
		return conflictos;
	}
	
	public int solve3(){
		for (NodoMateria materia : grafo.getMaterias()) {
			if (materia.getColores().size() == 1){
				coloreo[materia.getId()] = materia.getColores().get(0);
				for (NodoMateria vecino :materia.getAdyacentes()){
					if (coloreo[vecino.getId()] == materia.getColor(0)){
						conflictos++;
						//System.out.println("Hubo conflicto " + materia.getId() + "con el color: "+ coloreo[materia.getId()]);
					}
				}
			} else {
				boolean seteeColor = false; int colorFactible = 0;
				int i; int color; int posibilidades;int mayorPosibilidad = -1;
				int posiblesColores = 0; boolean tomeMuestra = false;
				Collections.shuffle(materia.getColores());
				for(i = 0 ; i < materia.getColores().size() && ! tomeMuestra;i++){
					posibilidades = 0;
					boolean colorValido = true;
					color =  materia.getColores().get(i);
					for (NodoMateria vecino :materia.getAdyacentes()){
						if (coloreo[vecino.getId()] == -1){
							if(vecino.getColores().size() == 1 && vecino.getColores().contains(color)){
								colorValido = false;
							}
							if (! vecino.getColores().contains(color)){
								posibilidades += vecino.getColores().size();
							} else {
								posibilidades += vecino.getColores().size() - 1;
							}
						} else {
							if (coloreo[vecino.getId()] == color){
								colorValido = false;
							}
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
					posiblesColores++;
					if(seteeColor && posiblesColores >Math.round(materia.getColores().size() * 0.2)){
						tomeMuestra = true;
					}
				}
				if (! seteeColor){
					// El goloso se encuentra con un conflicto.
					// Seteo el ultimo color.
					coloreo[materia.getId()] = colorFactible;
					conflictos++;
				}
			}
		}
		return conflictos;
	}
	
	public int solveSinPensar(){
		for (NodoMateria materia : grafo.getMaterias()) {
			coloreo[materia.getId()] = materia.getColores().get(0);
		}	
		return 0;
	}
	

}
// O (cantMaterias) * O (mayorVecindad) * ((NodoConMascolores) ^ 2) 