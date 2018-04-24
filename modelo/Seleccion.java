package modelo;

import java.util.ArrayList;

public interface Seleccion {
	
	public ArrayList<Individuo> selecciona(ArrayList<Individuo> pob, boolean minimizar);
}
