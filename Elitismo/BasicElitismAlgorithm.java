package Elitismo;

import java.util.Arrays;

import Population.Chromosome;


/*
 * Implementación del elitismo (hereda de ElitismAlgorithm).
 */

public class BasicElitismAlgorithm extends ElitismAlgorithm {
	
	public BasicElitismAlgorithm(double ep){
		this.elitismPercentage=ep;
	}	
	
	//La población que entra ya está ordenada de menor a mayor.
	//Coge los mejores individuos de la población (la elite).
	@Override
	public Chromosome[] pickElite( Chromosome[] population) {
		
		int n= (int) ((int)  population.length*this.elitismPercentage/100);
		Chromosome[] result= new Chromosome[n];
		
		//Devolvemos el resultado.
		for(int i=0; i<n; i++){
			result[i]=population[population.length-n+i].getCopy();
		}
		//El resultado también estará ordenado de menor a mayor.
		return result;
	}
	
	//La población está desordenada. La ordenamos he introducimos la élite, sustituyendola por los peores.
	//Inserta en la población los mejores individuos de la población (la élite).
	@Override
	public Chromosome[] putElite(Chromosome[] population, Chromosome[] elite) {
		
		//Ordenamos y quitamos los n peores por los n mejores de antes.
		Chromosome[] result= new Chromosome[population.length];
		
		Arrays.sort(population);
		
		for(int i=0; i<elite.length;i++){
			population[i]=elite[i];
		}
		
		Arrays.sort(population);
		
		for(int i=0; i<population.length; i++){
			result[i]=population[i].getCopy();
		}
		
		//La población resultante estará ordenada de peor a mejor fitness.
		return result;
	}
}
