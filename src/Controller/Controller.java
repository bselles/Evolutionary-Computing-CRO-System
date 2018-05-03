package Controller;

import java.util.ArrayList;

import Algorithms.CROAlgorithm;
import Population.Chromosome;

public class Controller {
	
	/**************************************************************************
	 * ATRIBUTOS
	 **************************************************************************/
	//En principio, la mayoría de estos atributos se obtienen de la interfaz.
	private int N;
	private int M;
	private int generations;		//Número de generaciones a evaluar.
	
	private double occupationRatio;	//Ratio inicial de celdas ocupadas.
	private double broodingRatio;	//Ratio de elementos que harán reproducción sexual externa
	private int survivingAttempts;	//Número de intentos que tiene cada larva para ubicarse en la población en la fase 3.
	private double arRatio;			//Porcentaje de soluciones que se intentarán reproducir asexualmente (fase 4).
	
	private double depredationPercentage; 	//Porcentaje de población a la que se aplicará la fase 5 (depredation).
	private double depredationProbability;	//Probabilidad de que cada individuo del porcentaje anterior muera (depredation phase).
	
	private double crossProbability;		//Probabilidad de que los individuos se crucen mediante reproducción sexual externa
	private double mutationProbability;		//Probabilidad de que ocurra la reproducción sexual interna
	
	private int problem; 			//Problema a resolver. Los posibles valores son de 1 a 5.
	private int n;					//En el caso de que resolvamos el problema 5, valor de n.
	
	public void realizarCalculos(){
		//Algoritmos que utilizaremos para la evaluación.
		CROAlgorithm cro =null; //Cuando se implemente la clase, instanciarla correctamente.
	
		//Generamos una población inicial.
		ArrayList<Chromosome> population= cro.generatePopulation(N, M, occupationRatio);
		ArrayList<Chromosome> fraction;
		ArrayList<Chromosome> water;	//Simulará el agua. Se almacenarán los resultados de los cruces, por ejemplo.
		
		//Bucle principal.
		for(int i=0; i<generations;i++){
			//Fase 1a: recogemos parte de la población para usarlo en la fase 2.
			fraction=cro.pickPopulationFraction(population, broodingRatio);
			//Fase 1b: Cruces e introducción del resultado en el agua.
			water=cro.externalSexualReproduction(fraction, crossProbability);
			//Fase 2: "mutación" (Reproducción sexual interna).
			water.addAll(cro.internalSexualReproduction(mutationProbability));
			//Fase 3:
			cro.putLarvaesIntoPopulation(population, water, survivingAttempts);
			//Fase 4:
			cro.asexualReproduction( population, arRatio, survivingAttempts);
			//Fase 5:
			cro.depredate(population, depredationPercentage, depredationProbability);
		}
	}
	
	public static void main(String[] args){
		Controller c= new Controller();
		c.realizarCalculos();
	}
}
