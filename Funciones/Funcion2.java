package Funciones;

import java.util.ArrayList;

import modelo.Individuo;
import modelo.IndividuoBinario;
import modelo.Reproduccion;

public class Funcion2 implements Funcion {
	private static double min = -512;
	private static double max = 512;
	
	private int longitud;
	private double convFact;
	
	private double probCruce;
	private double mejor;
	
	public double getMejor() {
		return mejor;
	}

	public Funcion2(double prec, double probCruce) {
		longitud = (int) (Math.log(1+(max-min)/prec) / Math.log(2));
		this.convFact = (max-min)/(Math.pow(2, longitud)-1) ;
		
		this.probCruce = probCruce;
	}
	
	private void evaluaIndividuo(IndividuoBinario ind) {
		ArrayList<Integer> dec = ind.bin2dec();
		double x1 = min + dec.get(0) * convFact;
		double x2 = min + dec.get(1) * convFact;
		double fitness = -(x2+47) * Math.sin(Math.sqrt(Math.abs( x2 + x1/2 + 47 ))) - x1 * Math.sin(Math.sqrt( Math.abs( x1 - (x2+47) )));
		ind.setFitness( fitness );
	}

	@Override
	public double evaluaPoblacion(ArrayList<Individuo> pob) {
		double media = 0.0;
		this.mejor = 0.0;
		for (Individuo individuo : pob) {
			evaluaIndividuo((IndividuoBinario) individuo);
			if(individuo.getFitness() < mejor)
				mejor = individuo.getFitness();
			media+=individuo.getFitness();
		}
		return media/pob.size();
	}
	@Override
	public Individuo generaIndividuo() {
		ArrayList<String> genotipo = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < longitud; j++) 
			if(Math.random() < 0.5)
				sb.append('0');
			else
				sb.append('1');
		
		genotipo.add(sb.toString());
		sb.setLength(0);
		for (int j = 0; j < longitud; j++) 
			if(Math.random() < 0.5)
				sb.append('0');
			else
				sb.append('1');
		genotipo.add(sb.toString());
		return new IndividuoBinario(genotipo);
	}

	@Override
	public ArrayList<Individuo> cruza(Individuo a, Individuo b, int tipo) {
		return Reproduccion.reproduce((IndividuoBinario)a, (IndividuoBinario)b, probCruce, tipo);
	}

	@Override
	public boolean getMinimizar() {
		return true;
	}
	

}
