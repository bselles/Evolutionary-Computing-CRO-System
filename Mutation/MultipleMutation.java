package Mutation;

import java.util.Random;

import Population.BinaryGen;
import Population.Chromosome;

/*
 * Implementa la mutación de cromosomas binarios:
 * Para cada gen de cada cromosoma, se evalua si muta cada uno de los bits que 
 * lo componen. En el caso de que un número aleatorio generado sea menor que 
 * mutationProbability, mutará. En caso contrario, no.
 * La mutación para binarios implica el cambio del bit que corresponde: si es true, 
 * cambiar a false y viceversa.
 */
public class MultipleMutation extends MutationAlgorithm{
	
	public MultipleMutation(double mutationP){
		this.mutationProbability=mutationP;
	}
	
	//Muta un cromosoma. 
	@Override
	public Chromosome mutateChromosome(Chromosome c){
		boolean[] bits;
		Chromosome result;
		result=c.getCopy();
		//Para cada gen del genotipo del cromosoma, mutamos.
		for(int i=0;i<result.getNumGens(); i++){
			bits=((BinaryGen) result.getGen(i)).getBits();
			for(int j=0; j<bits.length; j++){
				if(generate_random()<this.mutationProbability){
					if(bits[j]){
						bits[j]=false;
					}else{
						bits[j]=true;
					}
				}
			}
		}
		return result;
	}
	
	//Muta toda una población.
	@Override
	public Chromosome[] mutatePopulation(Chromosome[] p) {
		Chromosome[] result= new Chromosome[p.length];
		for(int i=0; i<p.length; i++){
			result[i]=mutateChromosome(p[i]);
		}
		return result;
	}
	
	//Genera un número aleatorio entre 0 y 1.
	private double generate_random(){
		Random r = new Random();
		return r.nextDouble();
	}
	
}
