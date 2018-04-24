package modelo;

import java.util.ArrayList;

public class IndividuoBinario implements Individuo {
	private ArrayList<String> genotipo;
	private double fitness;
	
	public IndividuoBinario(ArrayList<String> genotipo) {
		this.genotipo = genotipo;
	}

	public ArrayList<String> getGenotipo(){
		return this.genotipo;
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void setFitness(double fitness2) {
		fitness = fitness2;
	}

	@Override
	public void muta(int tipo, double prob, double num){
		switch (tipo) {
		case 1: //mutación cromosoma
			if(Math.random() <= prob){
				int gen = (int) (Math.random() * genotipo.size());
				StringBuffer aux = new StringBuffer(genotipo.get(gen));
				int pos = (int) (Math.random() * aux.length());
				aux.setCharAt(pos, aux.charAt(pos) == '0' ? '1' : '0');
				genotipo.set(gen, aux.toString());
			}
			break;
		case 2: //mutación gen a gen
			for (int i =0; i< genotipo.size(); i++ ) 
				if(Math.random() <= prob){
					StringBuffer aux = new StringBuffer(genotipo.get(i));
					int ind = (int)(Math.random()*aux.length());
					aux.setCharAt(ind, aux.charAt(ind) == '0' ? '1' : '0');
					genotipo.set(i, aux.toString());
				}
			break;

		default: //opción no reconocida -> se queda como está
			break;
		}
	}
	
	public ArrayList<Integer> bin2dec() {
		
		ArrayList<Integer> listaVar = new ArrayList<Integer>();
		
		for (int i = 0; i < this.genotipo.size(); i++) {
			String gen = genotipo.get(i);
			int acc = 0;
			int exp = 1;
			for(int j=0; j < gen.length(); j++) {
				if(gen.charAt(j) == '1') 
					acc+= exp;
				exp *=2;
			}
			listaVar.add(acc);
		}
		
		return listaVar;
				
	}

	@Override
	public Individuo clone() {
		ArrayList<String> nuevo =  new ArrayList<String>();
		for(String gen : this.genotipo){
			StringBuffer aux = new StringBuffer(gen);
			nuevo.add(aux.toString());
		}
		return new IndividuoBinario(nuevo);
	}

}
