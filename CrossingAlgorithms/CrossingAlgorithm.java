package CrossingAlgorithms;

import java.util.Random;

import Population.Chromosome;

//Clase abstracta que implementar�n todos los algoritmos de cruce.
public abstract class CrossingAlgorithm {
	
	public double crossP;
	
	//M�todos que deber�n implementar todos los algoritmos de cruce: crossChromosome y crossPopulation.
	public abstract Chromosome[] crossChromosome(Chromosome c1, Chromosome c2);
	public abstract Chromosome[] crossPopulation(Chromosome[] p);
	
	//Devuelve el �ndice del gen (dentro del array genotype) que se cruzar�.
	//Se selecciona un gen aleatorio.
	protected int chooseCrossingGen(int num_gen){
		return generateRandomIntNum(0,num_gen-1);
	}
	
	//Devuelve un punto de cruce aleatorio.
	protected int chooseCrossingPoint(double size){
		return generateRandomIntNum(1,(int)size-1);
	}
	
	//Devuelve un n�mero aleatorio entre 0 y 1.
	protected double generateRandomDoubleNum(){
		Random r = new Random();
		return r.nextDouble();
	}
	
	//Genera un n�mero aleatorio entre min y max. Min y max incluidos.
	protected int generateRandomIntNum(int min, int max){
		return (int)(Math.random() * (max) + min);
	}
}
