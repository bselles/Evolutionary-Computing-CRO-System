package modelo;

import java.util.ArrayList;

public class IndividuoReal implements Individuo {
	private ArrayList<Double> genotipo;
	private double fitness;
	
	public IndividuoReal(ArrayList<Double> genotipo) {
		this.genotipo = genotipo;
	}

	public ArrayList<Double> getGenotipo(){
		return this.genotipo;
	}

	@Override
	public double getFitness() {
		return fitness;
	}
	@Override
	public void setFitness(double fitness2) {
		this.fitness = fitness2;
	}

	@Override
	public void muta(int tipo, double prob, double num){
		switch (tipo) {
		case 1: //mutacion 
			for (int i =0; i< genotipo.size(); i++ )  
				if(Math.random() < prob)
					genotipo.set(i, Math.random() * Math.PI);
				
			break;

		default: //opcion no reconocida -> se queda como esta
			return ;
		}
	}

	@Override
	public Individuo clone() {
		ArrayList<Double> nuevo =  new ArrayList<Double>();
		for(Double gen : this.genotipo)
			nuevo.add(gen.doubleValue());
		return new IndividuoReal(nuevo);
	}
}
