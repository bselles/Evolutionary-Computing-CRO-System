import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Funciones.Funcion;
import modelo.Individuo;
import modelo.Seleccion;


public class AlgoritmoGenetico {

	private ArrayList<Individuo> pob;
	private ArrayList<Individuo> elite;
	private int tam_pob;
	private Individuo elMejor;
	
	//int pos_mejor; pob.getIndex
	private double prob_mut;
	private int mutacion;
	private Funcion func;
	private Seleccion seleccion;
	private int cruce;

	
	public AlgoritmoGenetico(int tam_pob, double prob_mut, int tipo_mut, 
			int tipo_cruce, Funcion func, Seleccion selecc) {
		this.pob =new ArrayList<Individuo>();
		this.elite = new ArrayList<Individuo>();
		this.tam_pob = tam_pob;
		this.elMejor = null;
		this.prob_mut = prob_mut;
		this.func = func;
		this.mutacion = tipo_mut;
		this.seleccion = selecc;
		this.cruce = tipo_cruce;
	}

	public void construyePoblacion() {
		for(int i=0; i<tam_pob; i++) 
			pob.add(func.generaIndividuo());
	}
	
	public double getElMejor() {
		return elMejor.getFitness();
	}
	
	public double evaluarPoblacion(){
		return func.evaluaPoblacion(pob);
	}
	
	public double mejorPoblacion(){
		return func.getMejor();
	}
	
	
	public void seleccion(){
		pob = seleccion.selecciona(pob, func.getMinimizar());
	}
	
	public void reproduccion(){
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		for(int i = 0; i<tam_pob-1; i+=2){
			hijos.addAll(func.cruza(pob.get(i), pob.get(i+1), cruce)); //Donde está el 1 habría que pasar el tipo de reproducción
			if(i+3 == tam_pob){
				hijos.add(pob.get(i+2));
				break;
			}
		}
		pob=hijos;
	}
	
	public void mutacion(){
		for(int i=0; i<tam_pob; i++) {
			pob.get(i).muta(mutacion, prob_mut,0.0);
		}
	}
	
	public void elitismo(int elit){
		ArrayList<Individuo> ord = new ArrayList<Individuo>();
		ord.addAll(ordena(pob, func.getMinimizar()));
		for(int i=0; i<elit; i++)
			elite.add(ord.get(i).clone());
	}
	
	public void sustituyeElite(int elit){
		int rand;
		for(int i=0; i<elit; i++){
			rand = (int)(Math.random()*pob.size());
			pob.remove(rand);
		}
		for(int i=0; i<elit; i++)
			pob.add(elite.get(i));
		
		elite.clear();
		
	}
	
	private ArrayList<Individuo> ordena(ArrayList<Individuo> pob, boolean minimizar){
		
		
		Collections.sort(pob, new Comparator<Individuo>(){

			@Override
			public int compare(Individuo o1, Individuo o2) {
				Double d1,d2;
				d1 = o2.getFitness();
				d2 = o1.getFitness();
				if(minimizar)
					return d2.compareTo(d1);
				else
					return d1.compareTo(d2);
			}
				
		});
		
		return pob ;
		
	}
}
