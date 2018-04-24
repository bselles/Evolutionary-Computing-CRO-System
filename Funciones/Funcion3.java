package Funciones;

import java.util.ArrayList;

import modelo.Individuo;
import modelo.IndividuoBinario;
import modelo.Reproduccion;



public class Funcion3 implements Funcion {
	private static double min1 = -3.0;
	private static double min2 = 4.1;
	private static double max1 = 12.1;
	private static double max2 = 5.8;
	
	private int longitud1;
	private double convFact1;
	
	private int longitud2;
	private double convFact2;
	private double mejor;

	
	private double probCruce;
	
	public Funcion3(double prec, double probCruce) {
		longitud1 = (int) (Math.log(1+(max1-min1)/prec) / Math.log(2));
		this.convFact1 = (max1-min1)/(Math.pow(2, longitud1)-1) ;
		
		this.probCruce = probCruce;
		longitud2 = (int) (Math.log(1+(max2-min2)/prec) / Math.log(2));
		this.convFact2 = (max2-min2)/(Math.pow(2, longitud2)-1) ;
	}
	
	private void evaluaIndividuo(IndividuoBinario ind) {
		ArrayList<Integer> dec = ind.bin2dec();
		double x1 = min1 + dec.get(0) * convFact1;
		double x2 = min2 + dec.get(1) * convFact2;
		double fitness = 21.5 + x1 * Math.sin( 4 * Math.PI * x1 ) + x2 * Math.sin( 20 * Math.PI * x2 );
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

	public double getMejor() {
		return mejor;
	}

	@Override
	public Individuo generaIndividuo() {
		ArrayList<String> genotipo = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < longitud1; j++) 
			if(Math.random() < 0.5)
				sb.append('0');
			else
				sb.append('1');
		
		genotipo.add(sb.toString());
		sb.setLength(0);
		for (int j = 0; j < longitud2; j++) 
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
		return false;
	}

}
