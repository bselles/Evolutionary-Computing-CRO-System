package Funciones;

import java.util.ArrayList;

import modelo.Individuo;

public interface Funcion {
	
	public double evaluaPoblacion(ArrayList<Individuo> pob);	
	
	public Individuo generaIndividuo();
	
	public ArrayList<Individuo> cruza(Individuo a, Individuo b, int tipo);

	public double getMejor();
	
	public boolean getMinimizar();
	
	
}
