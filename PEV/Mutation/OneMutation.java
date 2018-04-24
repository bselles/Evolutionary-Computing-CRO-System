package Mutation;

import java.util.Random;

import Population.BinaryGen;
import Population.Chromosome;

/*
 * Implementa la mutaci�n de cromosomas binarios:
 * Se genera un n�mero aleatorio para determinar qu� gen mutar� y otro 
 * para determinar qu� bit del gen. Luego se genera otro para determinar si muta o no.
 * En el caso de que un n�mero aleatorio generado sea menor que 
 * mutationProbability, mutar�. En caso contrario, no.
 * La mutaci�n para binarios implica el cambio del bit que corresponde: si es true, 
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
	
	//Muta una poblaci�n entera.
	@Override
	public Chromosome[] mutatePopulation(Chromosome[] p) {
		Chromosome[] result= new Chromosome[p.length];
		for(int i=0; i<p.length; i++){
			result[i]=mutateChromosome(p[i]);
		}
		return result;
	}
	
	
	//Genera un n�mero double aleatorio entre 0 y 1.
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
