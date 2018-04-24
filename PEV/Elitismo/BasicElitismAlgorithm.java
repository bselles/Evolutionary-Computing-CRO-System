package Elitismo;

import java.util.Arrays;

import Population.Chromosome;


/*
 * Implementaci�n del elitismo (hereda de ElitismAlgorithm).
 */

public class BasicElitismAlgorithm extends ElitismAlgorithm {
	
	public BasicElitismAlgorithm(double ep){
		this.elitismPercentage=ep;
	}	
	
	//La poblaci�n que entra ya est� ordenada de menor a mayor.
	//Coge los mejores individuos de la poblaci�n (la elite).
	@Override
	public Chromosome[] pickElite( Chromosome[] population) {
		
		int n= (int) ((int)  population.length*this.elitismPercentage/100);
		Chromosome[] result= new Chromosome[n];
		
		//Devolvemos el resultado.
		for(int i=0; i<n; i++){
			result[i]=population[population.length-n+i].getCopy();
		}
		//El resultado tambi�n estar� ordenado de menor a mayor.
		return result;
	}
	
	//La poblaci�n est� desordenada. La ordenamos he introducimos la �lite, sustituyendola por los peores.
	//Inserta en la poblaci�n los mejores individuos de la poblaci�n (la �lite).
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
		
		//La poblaci�n resultante estar� ordenada de peor a mejor fitness.
		return result;
	}
}
