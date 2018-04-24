package Funciones;

import java.util.ArrayList;

import modelo.Individuo;
import modelo.IndividuoBinario;
import modelo.IndividuoReal;
import modelo.Reproduccion;

public class Funcion5 implements Funcion {
	private static double min = 0;
	private static double max = Math.PI;
	
	private boolean tipo; //0 Binario, 1 Real
	private int longitud;
	private double convFact;
	private int genes;
	private double mejor;
	
	private double probCruce;
	
	public Funcion5(boolean tipo, double prec, double probCruce, int genes) {
		longitud = (int) (Math.log(1+(max-min)/prec) / Math.log(2));
		this.convFact = (max-min)/(Math.pow(2, longitud)-1) ;
		this.genes=genes;
		this.tipo=tipo;
		
		this.probCruce = probCruce;
	}
	
	private void evaluaIndividuo(IndividuoBinario ind) {
		ArrayList<Integer> dec = ind.bin2dec();
		double fitness = 0.0;
		for (int i = 1; i <= genes; i++) {
			double x = min + dec.get(i-1) * convFact;
			fitness += Math.sin(x) * Math.pow( Math.sin( (i+1) * x*x / Math.PI ), 20);
		}
		ind.setFitness( -fitness);
	}
	
	private void evaluaIndividuo(IndividuoReal ind){
		double fitness = 0.0;
		ArrayList<Double> genotipo = ind.getGenotipo();
		double x;
		for (int i = 1; i <= genes; i++) {
			x = genotipo.get(i-1);
			fitness += Math.sin(x) * Math.pow( Math.sin( (i+1) * x*x / Math.PI ), 20);
		}
		ind.setFitness( -fitness);
	}
	
	public double getMejor() {
		return mejor;
	}

	@Override
	public double evaluaPoblacion(ArrayList<Individuo> pob) {
		double media = 0.0;
		this.mejor = 0.0;
		for (Individuo individuo : pob) {
			if(tipo)
				evaluaIndividuo((IndividuoReal)individuo);
			else 
				evaluaIndividuo((IndividuoBinario)individuo);
			if(individuo.getFitness() < mejor)
				mejor = individuo.getFitness();
			media+=individuo.getFitness();
		}
		return media/pob.size();
	}

	@Override
	public Individuo generaIndividuo() {
		if(tipo)
			return generaReal();
		else 
			return generaBinario();
	}
	

	private IndividuoBinario generaBinario() {
		ArrayList<String> genotipo = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for(int i =0; i < genes; i++){
			sb.setLength(0);
			for (int j = 0; j < longitud; j++) 
				if(Math.random() < 0.5)
					sb.append('0');
				else
					sb.append('1');
			genotipo.add(sb.toString());
		}
		return new IndividuoBinario(genotipo);
	}

	private IndividuoReal generaReal() {
		ArrayList<Double> genotipo = new ArrayList<Double>();
		for(int i =0; i < genes; i++)
			genotipo.add(Math.random() * max);
		return new IndividuoReal(genotipo);
	}

	@Override
	public ArrayList<Individuo> cruza(Individuo a, Individuo b, int tipo) {
		if(this.tipo)
			return Reproduccion.reproduce((IndividuoReal)a, (IndividuoReal)b, probCruce, tipo);
		else
			return Reproduccion.reproduce((IndividuoBinario)a, (IndividuoBinario)b, probCruce, tipo);
	}

	@Override
	public boolean getMinimizar() {
		return true;
	}
	

}
