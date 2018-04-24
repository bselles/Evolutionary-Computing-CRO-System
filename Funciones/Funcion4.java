package Funciones;

import java.util.ArrayList;

import modelo.Individuo;
import modelo.IndividuoBinario;
import modelo.Reproduccion;

public class Funcion4 implements Funcion {
	private static double min = -10;
	private static double max = 10;
	
	private int longitud;
	private double convFact;
	
	private double probCruce;
	private double mejor;
	
	public Funcion4(double prec, double probCruce) {
		longitud = (int) (Math.log(1+(max-min)/prec) / Math.log(2));
		this.convFact = (max-min)/(Math.pow(2, longitud)-1) ;
		
		this.probCruce = probCruce;
	}
	
	private void evaluaIndividuo(IndividuoBinario ind) {
		ArrayList<Integer> dec = ind.bin2dec();
		double x1 = min + dec.get(0) * convFact;
		double x2 = min + dec.get(1) * convFact;
		double acum1 = 0;
		double acum2 = 0;
		for (int i = 1; i < 6; i++) {
			acum1 += i * Math.cos((i+1)*x1 + i );
			acum2 += i * Math.cos((i+1)*x2 + i );
		}
		ind.setFitness( acum1*acum2 );
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

	public double getMejor() {
		return mejor;
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
