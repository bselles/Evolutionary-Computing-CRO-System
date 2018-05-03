package Controller;

import java.util.ArrayList;

import Algorithms.CROAlgorithm;
import Population.Chromosome;

public class Controller {
	
	/**************************************************************************
	 * ATRIBUTOS
	 **************************************************************************/
	//En principio, la mayor�a de estos atributos se obtienen de la interfaz.
	private int N;
	private int M;
	private int generations;		//N�mero de generaciones a evaluar.
	
	private double occupationRatio;	//Ratio inicial de celdas ocupadas.
	private int survivingAttempts;	//N�mero de intentos que tiene cada larva para ubicarse en la poblaci�n en la fase 3.
	private double arRatio;			//Porcentaje de soluciones que se intentar�n reproducir asexualmente (fase 4).
	
	private double depredationPercentage; 	//Porcentaje de poblaci�n a la que se aplicar� la fase 5 (depredation).
	private double depredationProbability;	//Probabilidad de que, a cada individuo del porcentaje anterior, muera (depredation phase).
	
	private int problem; 			//Problema a resolver. Los posibles valores son de 1 a 5.
	private int n;					//En el caso de que resolvamos el problema 5, valor de n.
	
	public void realizarCalculos(){
		//Algoritmos que utilizaremos para la evaluaci�n.
		CROAlgorithm cro =null; //Cuando se implemente la clase, instanciarla correctamente.
	
		//Generamos una poblaci�n inicial.
		ArrayList<Chromosome> population= cro.generatePopulation(N, M, occupationRatio);
		ArrayList<Chromosome> fraction;
		ArrayList<Chromosome> water;	//Simular� el agua. Se almacenar�n los resultados de los cruces, por ejemplo.
		
		//Bucle principal.
		for(int i=0; i<generations;i++){
			//Fase 1a: recogemos parte de la poblaci�n para usarlo en la fase 2.
			fraction=cro.pickPopulationFraction(population);
			//Fase 1b: Cruces e introducci�n del resultado en el agua.
			water=cro.externalSexualReproduction(population);
			//Fase 2: "mutaci�n" (Reproducci�n sexual interna).
			water.addAll(cro.internalSexualReproduction(fraction));
			//Fase 3:
			population=cro.putLarvaesIntoPopulation(survivingAttempts, population, water);
			//Fase 4:
			population=cro.asexualReproduction(arRatio, population);
			//Fase 5:
			population=cro.depredate(depredationPercentage, depredationProbability, population);
		}
	}
	
	public static void main(String[] args){
		Controller c= new Controller();
		c.realizarCalculos();
	}
}
