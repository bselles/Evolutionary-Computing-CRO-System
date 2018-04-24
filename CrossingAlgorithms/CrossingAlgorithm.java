package CrossingAlgorithms;

import java.util.Random;

import Population.Chromosome;

//Clase abstracta que implementarán todos los algoritmos de cruce.
public abstract class CrossingAlgorithm {
	
	public double crossP;
	
	//Métodos que deberán implementar todos los algoritmos de cruce: crossChromosome y crossPopulation.
	public abstract Chromosome[] crossChromosome(Chromosome c1, Chromosome c2);
	public abstract Chromosome[] crossPopulation(Chromosome[] p);
	
	//Devuelve el índice del gen (dentro del array genotype) que se cruzará.
	//Se selecciona un gen aleatorio.
	protected int chooseCrossingGen(int num_gen){
		return generateRandomIntNum(0,num_gen-1);
	}
	
	//Devuelve un punto de cruce aleatorio.
	protected int chooseCrossingPoint(double size){
		return generateRandomIntNum(1,(int)size-1);
	}
	
	//Devuelve un número aleatorio entre 0 y 1.
	protected double generateRandomDoubleNum(){
		Random r = new Random();
		return r.nextDouble();
	}
	
	//Genera un número aleatorio entre min y max. Min y max incluidos.
	protected int generateRandomIntNum(int min, int max){
		return (int)(Math.random() * (max) + min);
	}
}
