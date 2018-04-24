package Mutation;

import Population.Chromosome;

/*
 * Clase abstracta que representa el algoritmo de mutación.
 * Todos los algoritmos que implementen la mutación heredarán de esta clase.
 */
public abstract class MutationAlgorithm {
	
	public double mutationProbability;
	
	public  abstract Chromosome mutateChromosome(Chromosome c);
	public abstract Chromosome[] mutatePopulation(Chromosome[] p);
}
