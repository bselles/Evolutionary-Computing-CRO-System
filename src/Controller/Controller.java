package Controller;

import org.math.plot.utils.Array;

import java.util.ArrayList;
import java.util.List;

import Algorithms.CROAlgorithm;
import Population.Chromosome;

public class Controller {

	public interface Callback {
		void onGenerationOver(int generation, EvaluateValues evaluateValues, List<Chromosome> population);
	}

	private Callback callback;

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	/**************************************************************************
	 * ATRIBUTOS
	 **************************************************************************/
	
	private int rows;
	private int cols;
	private int generations;				//Numero de generaciones a evaluar.
	
	//Fase 0: ocupar el arrecife
	private double occupationRatio;			//Ratio inicial de celdas ocupadas.
	
	//Fase 1a: dividir la poblacion entre rep sex externa o interna
	private double broadcastRatio;			//Ratio de elementos que haran reproduccion sexual externa
	//Fase 1b: aplicar la reproduccion sexual externa
	private double crossProbability;		//Probabilidad de que los individuos se crucen mediante reproduccion sexual externa
	private int crossType;                  //para seleccionar el tipo de cruce
	
	//Fase 2: aplicar la reproduccion sexual interna
	private double broodingProbability;		//Probabilidad de que ocurra una modificacion durante la reproduccion sexual interna
	private int mutationType;               //para seleccionar el tipo de mutacion
	
	//Fase 3 y 4: establecer larvaes
	private int survivingAttempts;			//Numero de intentos que tiene cada larva para ubicarse en la poblacion.
	
	//Fase 4: elegir los individuos que intentaran hacer la reproduccion asexual
	private double asexReprRatio;			//Ratio de elementos que realizaran una reproduccion asexual
	
	//Fase 5: fase de depredacion de arrecifes
	private double depredationPercentage; 	//Porcentaje de poblacion a la que se aplicara la depredacion.
	private double depredationProbability;	//Probabilidad de que cada individuo del porcentaje anterior muera.
	

	private int problem; 					//Problema a resolver. Los posibles valores son de 1 a 5.
	private int nGenes;							//En el caso de que resolvamos el problema 5, valor de n.

	
	
	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setGenerations(int generations) {
		this.generations = generations;
	}

	public void setOccupationRatio(double occupationRatio) {
		this.occupationRatio = occupationRatio;
	}

	public void setBroadcastRatio(double broadcastRatio) {
		this.broadcastRatio = broadcastRatio;
	}

	public void setCrossProbability(double crossProbability) {
		this.crossProbability = crossProbability;
	}

	public void setCrossType(int crossType) {
		this.crossType = crossType;
	}

	public void setBroodingProbability(double broodingProbability) {
		this.broodingProbability = broodingProbability;
	}

	public void setMutationType(int mutationType) {
		this.mutationType = mutationType;
	}

	public void setSurvivingAttempts(int survivingAttempts) {
		this.survivingAttempts = survivingAttempts;
	}

	public void setAsexReprRatio(double asexReprRatio) {
		this.asexReprRatio = asexReprRatio;
	}

	public void setDepredationPercentage(double depredationPercentage) {
		this.depredationPercentage = depredationPercentage;
	}

	public void setDepredationProbability(double depredationProbability) {
		this.depredationProbability = depredationProbability;
	}

	public void setProblem(int problem) {
		this.problem = problem;
	}

	public void setnGenes(int nGenes) {
		this.nGenes = nGenes;
	}

	
	public void realizarCalculos() {
		//Suponemos que tenemos estos parametros en la interfaz.
//		this.N=10;
//		this.M=10;
//		this.generations=200;
//		this.occupationRatio=0.3;
//		this.broodingRatio=0.3;
//		this.survivingAttempts=5;
//		this.arRatio=0.2;
//		this.depredationPercentage=0.05;
//		this.depredationProbability=0.1;
//		this.crossProbability=1;
//		this.mutationProbability=1;
//		this.mutationType=1;
//		this.crossType=3;
//		this.problem=3;
//		this.n=3;
		

		//Algoritmos que utilizaremos para la evaluacion.
		CROAlgorithm cro =new CROAlgorithm(problem, nGenes, mutationType, crossType); //Cuando se implemente la clase, instanciarla correctamente.
	
		//Generamos una poblaci�n inicial.
		ArrayList<Chromosome> population= cro.generatePopulation(rows, cols, occupationRatio);
		ArrayList<Chromosome> fraction;
		ArrayList<Chromosome> water;	//Simular� el agua. Se almacenar�n los resultados de los cruces, por ejemplo.
		
		//Generamos uno aleatorio.
		Chromosome winner = null;
		
		//Bucle principal.
		for(int i=0; i<generations;i++){
			winner = cro.generateChromosome();
			Chromosome loser = cro.generateChromosome();
			//cro.printPopulation(N,M,population);
			//System.out.println("---------------------------------");


			//Fase 1a: recogemos parte de la poblacion para usarlo en la fase 2.
			fraction=cro.pickPopulationFraction(population, broadcastRatio);
			//Fase 1b: Cruces e introduccion del resultado en el agua.
			water=cro.externalSexualReproduction(fraction, crossProbability);
			//Fase 2: "mutacion" (Reproduccion sexual interna).
			water.addAll(cro.internalSexualReproduction(broodingProbability) );
			//Fase 3:
			population=cro.putLarvaesIntoPopulation(population, water, survivingAttempts);
			//Fase 4:
			//POPULATION SE MODIFICA POR REFERENCIA!!!!! ---------> CUIDADO!
			cro.asexualReproduction( population, asexReprRatio, survivingAttempts);
			//Fase 5:
			population=cro.depredate(population, depredationPercentage, depredationProbability);

			EvaluateValues results = new EvaluateValues();
			double notNull = 0;
			results.averageFitness = 0;
			//Cogemos el mejor de esa generacion.
			for (int j=0; j<population.size(); j++){

				Chromosome chromosome = population.get(j);
				if (chromosome != null) {
					//Si ese individuo de la poblacion es "mejor" que el actual ganador...
					if (winner.compareTo(chromosome) < 0) {
						winner = chromosome.getCopy();
					}
					if (loser.compareTo(chromosome) > 0) {
						loser = chromosome.getCopy();
					}
					results.averageFitness += chromosome.getFitness();
					notNull++;
				}
			}
			results.worstFitness = loser.getFitness();
			results.bestFitness = winner.getFitness();
			if (notNull > 0) {
				results.averageFitness = results.averageFitness / notNull;
			}
			callback.onGenerationOver(i, results, population);
		}
		System.out.println("El mejor individuo ha sido: ");
		System.out.print(winner.toString());
	}

}
