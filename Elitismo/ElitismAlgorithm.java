package Elitismo;

import Population.Chromosome;

/*
 * Clase abstracta que representa los distintas formas de aplicar elitismo.
 * Incluye todos los métodos/funciones que deben implementar cualquier algoritmo 
 * que realice el elitismo.
 */
public abstract class ElitismAlgorithm {
	
	//Porcentaje de elitismo aplicado. Se espera que la entrada sea un número entero (por ejemplo 5%).
	public double elitismPercentage;
	
	public abstract Chromosome[] pickElite( Chromosome[] population);
	public abstract Chromosome[] putElite(Chromosome[] population,Chromosome[] elite);

}
