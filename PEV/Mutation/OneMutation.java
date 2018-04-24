package Mutation;

import java.util.Random;

import Population.BinaryGen;
import Population.Chromosome;

/*
 * Implementa la mutación de cromosomas binarios:
 * Se genera un número aleatorio para determinar qué gen mutará y otro 
 * para determinar qué bit del gen. Luego se genera otro para determinar si muta o no.
 * En el caso de que un número aleatorio generado sea menor que 
 * mutationProbability, mutará. En caso contrario, no.
 * La mutación para binarios implica el cambio del bit que corresponde: si es true, 
 * cambiar a false y viceversa.
 */
public class OneMutation extends MutationAlgorithm{
	
	public OneMutation(double mutationP){
		this.mutationProbability=mutationP;
	}
	
	//Muta un solo cromosoma. 
	@Override
	public Chromosome mutateChromosome(Chromosome c){
		boolean[] bits;
		Chromosome result;
		result=c.getCopy();
		//mg=mutated gen.
		bits=((BinaryGen) result.getGen(generate_random_int(result.getNumGens()))).getBits();
		if(generate_random()<this.mutationProbability){
			int i=generate_random_int(bits.length);
			if(bits[i]){
				bits[i]=false;
			}else{
				bits[i]=true;
			}
		}
		return result;
	}
	
	//Muta una población entera.
	@Override
	public Chromosome[] mutatePopulation(Chromosome[] p) {
		Chromosome[] result= new Chromosome[p.length];
		for(int i=0; i<p.length; i++){
			result[i]=mutateChromosome(p[i]);
		}
		return result;
	}
	
	
	//Genera un número double aleatorio entre 0 y 1.
	private double generate_random(){
		Random r = new Random();
		return r.nextDouble();
	}
	
	//Genera un int aleatorio de 0 a max (max sin incluir).
	private int generate_random_int(int max){
		Random ran=new Random();
		return ran.nextInt(max);
	}
	
}
