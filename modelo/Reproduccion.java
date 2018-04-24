package modelo;

import java.util.ArrayList;

public class Reproduccion {

	static public ArrayList<Individuo> reproduce(IndividuoBinario a, IndividuoBinario b, double probCruce, int tipo) {
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		ArrayList<String> hijoA = new ArrayList<String>(), hijoB = new ArrayList<String>();
		
		switch (tipo) {
		case 1: //cruce monopunto en cromosoma
			if(a.getGenotipo().size()>1){
				if(Math.random() > probCruce) { //no se cruzan
					hijoA = a.getGenotipo();
					hijoB = b.getGenotipo();
					break;
				}
				
				int[] longAcum = new int[a.getGenotipo().size()];
				
				longAcum[0] = a.getGenotipo().get(0).length();
				
				for (int i = 1; i < longAcum.length; i++) 
					longAcum[i] = longAcum[i-1] + a.getGenotipo().get(i).length();
				
				int pos = (int) (Math.random() * longAcum[longAcum.length-1]);
					
				int gen;
				
				for (gen = 0; gen < longAcum.length; gen++) 
					if(longAcum[gen] > pos)
						break;
				
				if(gen > 0)
					pos = pos - longAcum[gen-1];
				
				int j;
				for( j = 0; j < gen; j++){
					hijoA.add(new StringBuffer(a.getGenotipo().get(j)).toString());
					hijoB.add(new StringBuffer(b.getGenotipo().get(j)).toString());
				}
				
				hijoA.add(a.getGenotipo().get(j).substring(0, pos).concat(b.getGenotipo().get(j).substring(pos)));
				hijoB.add(b.getGenotipo().get(j).substring(0, pos).concat(a.getGenotipo().get(j).substring(pos)));
				
				for ( j++; j < a.getGenotipo().size(); j++) {
					hijoA.add(new StringBuffer(b.getGenotipo().get(j)).toString());
					hijoB.add(new StringBuffer(a.getGenotipo().get(j)).toString());
				}
	
				break;
			}
		case 2: //cruce monopunto gen a gen
			if(Math.random() > probCruce) { //no se cruzan
				hijoA = a.getGenotipo();
				hijoB = b.getGenotipo();
				break;
			}
			for(int i = 0; i < a.getGenotipo().size(); i++){
				String genA = a.getGenotipo().get(i), genB = b.getGenotipo().get(i);
				int pos1 = (int) (Math.random() * (genA.length()-1))+1;
				hijoA.add(genA.substring(0, pos1).concat(genB.substring(pos1)));
				hijoB.add(genB.substring(0, pos1).concat(genA.substring(pos1)));
				
			}

			break;

		default:
			break;
		}
		
		hijos.add(new IndividuoBinario(hijoA));
		hijos.add(new IndividuoBinario(hijoB));
		return hijos;
	}

	static public ArrayList<Individuo> reproduce(IndividuoReal a, IndividuoReal b, double probCruce, int tipo) { //ops no hace nada
		ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		ArrayList<Double> hijoA = new ArrayList<Double>(), hijoB = new ArrayList<Double>();
		int punto, i;
		double alfa;
		
		switch (tipo) {
		case 1: //un punto
			if(Math.random() > probCruce) { //no se cruzan
				hijoA = a.getGenotipo();
				hijoB = b.getGenotipo();
				break;
			}
			
			punto = (int) (Math.random() * a.getGenotipo().size());
			for(i = 0; i<punto; i++) {
				hijoA.add(a.getGenotipo().get(i));
				hijoB.add(b.getGenotipo().get(i));
			}
			for( ; i<a.getGenotipo().size(); i++) {
				hijoA.add(b.getGenotipo().get(i));
				hijoB.add(a.getGenotipo().get(i));
			}
			
			break;
		case 2: //discreto uniforme
			punto = (int) (Math.random() * a.getGenotipo().size());
			for(i = 0; i<a.getGenotipo().size(); i++) {
				if(Math.random() > probCruce) { 
					hijoA.add(a.getGenotipo().get(i));
					hijoB.add(b.getGenotipo().get(i));
				} else {
					hijoA.add(b.getGenotipo().get(i));
					hijoB.add(a.getGenotipo().get(i));
				}
			}

			break;
			
		case 3: //aritmetico
			alfa = Math.random();
			for(i = 0; i<a.getGenotipo().size(); i++) {
				hijoA.add(a.getGenotipo().get(i) * alfa + b.getGenotipo().get(i) * (1-alfa));
				hijoB.add(b.getGenotipo().get(i) * alfa + a.getGenotipo().get(i) * (1-alfa));
			}
			break;
		case 4: //Simulated Binary CrossOver )
			alfa = Math.random();
			double n = Math.random() + 1;
			double beta = alfa < 0.5 ? 2 * Math.pow(alfa, 1/(n+1) ) : Math.pow( 1/ (2 - 2*alfa) , 1/(n+1) );
			for(i = 0; i<a.getGenotipo().size(); i++) {
				hijoA.add( 0.5 * (a.getGenotipo().get(i) + b.getGenotipo().get(i) - beta * Math.abs(b.getGenotipo().get(i) - a.getGenotipo().get(i)) ));
				hijoB.add( 0.5 * (a.getGenotipo().get(i) + b.getGenotipo().get(i) + beta * Math.abs(b.getGenotipo().get(i) - a.getGenotipo().get(i)) ));
			}
			break;
				
		default:
			break;
		}
		
		hijos.add(new IndividuoReal(hijoA));
		hijos.add(new IndividuoReal(hijoB));
		return hijos;
	}

}