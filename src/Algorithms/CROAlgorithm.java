package Algorithms;

import java.util.ArrayList;

import Population.Chromosome;

public abstract class CROAlgorithm {
	
	public abstract ArrayList<Chromosome> generatePopulation(int N, int M, double occupationRatio);	

	/*M�todo asociado a la parte 1a: Broadcast Spawning.
	Seleccionamos una fracci�n de la poblaci�n total y la apartamos.
	Esta fracci�n se usar� en la fase 2 (Brooding)*/
	public abstract ArrayList<Chromosome> pickPopulationFraction(ArrayList<Chromosome> population);

	/*
	 * Representa la fase 1b. Se reproducen por parejas y lanzan sus hijos al mar.
	 */
	public abstract ArrayList<Chromosome> externalSexualReproduction(ArrayList<Chromosome> population);

	/*
	 * Representa la fase 2.
	 */
	public abstract ArrayList<Chromosome> internalSexualReproduction(ArrayList<Chromosome> fraction);

	
	/*
	 * Representa la fase 3.
	 * Las larvas generadas en la fase 1 y 2, que est�n en el agua, luchan por situarse en el coral (population).
	 * 
	 */
	public abstract ArrayList<Chromosome> putLarvaesIntoPopulation(int survivingAttempts,ArrayList<Chromosome> population, ArrayList<Chromosome> water);

	/*
	 * Representa la fase 4. 
	 * Reproducci�n asexual.
	 */
	public abstract ArrayList<Chromosome> asexualReproduction(double ratio,ArrayList<Chromosome> population);

	/*
	 * Representa la fase 5.
	 * Se eliminan ciertos individuos de la poblaci�n (depredation).
	 */
	public abstract ArrayList<Chromosome> depredate(double ratio, double probability, ArrayList<Chromosome> population);
}
