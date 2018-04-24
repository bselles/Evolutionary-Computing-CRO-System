package Mutation;

import Population.Chromosome;

/*
 * Clase abstracta que representa el algoritmo de mutaci�n.
 * Todos los algoritmos que implementen la mutaci�n heredar�n de esta clase.
 */
public abstract class MutationAlgorithm {
	
	public double mutationProbability;
	
	public  abstract Chromosome mutateChromosome(Chromosome c);
	public abstract Chromosome[] mutatePopulation(Chromosome[] p);
}
