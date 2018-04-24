package Funciones;

import java.util.ArrayList;

import modelo.Individuo;
import modelo.IndividuoBinario;
import modelo.Reproduccion;

public class Funcion1 implements Funcion {
	private static double min = 0;
	private static double max = 32;
	
	private int longitud;
	private double convFact;
	private double probCruce;
	private double mejor;
	
	public Funcion1(double prec, double probCruce) {
		longitud = (int) (Math.log(1+(max-min)/prec) / Math.log(2));
		this.convFact = (max-min)/(Math.pow(2, longitud)-1) ;
		this.probCruce = probCruce;
	}
	
	private void evaluaIndividuo(IndividuoBinario ind) {
		double x = min + ind.bin2dec().get(0) * convFact;
		double fitness = 20 + Math.E - 20 * Math.exp(-0.2 * Math.abs(x)) - Math.exp( Math.cos(2*Math.PI*x) );
		ind.setFitness( fitness );
	}

	@Override
	public double evaluaPoblacion(ArrayList<Individuo> pob) {
		double media = 0.0;
		this.mejor = 0.0;
		for (Individuo individuo : pob) {
			evaluaIndividuo((IndividuoBinario) individuo);
			if(individuo.getFitness() > mejor)
				mejor = individuo.getFitness();
			media+=individuo.getFitness();
		}
		return media/pob.size();
	}

	@Override
	public double getMejor() {
		return mejor;
	}

	@Override
	public Individuo generaIndividuo() {
		ArrayList<String> genotipo = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < longitud; i++) 
			if(Math.random() < 0.5)
				sb.append('0');
			else
				sb.append('1');
		
		genotipo.add(sb.toString());
		return new IndividuoBinario(genotipo);
	}

	@Override
	public ArrayList<Individuo> cruza(Individuo a, Individuo b, int tipo) {
		return Reproduccion.reproduce((IndividuoBinario)a, (IndividuoBinario)b, probCruce,  tipo);
	}

	@Override
	public boolean getMinimizar() {
		return false;
	}

	
	
}
