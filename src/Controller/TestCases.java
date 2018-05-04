package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Algorithms.CROAlgorithm;
import Population.Chromosome;

public class TestCases {
	
	/*Carga las soluciones de los problemas de 1 a 5.
	En 1 contendrá la solución del problema 1, etc.
	El problema 5 tiene distintos valores, en función del valor de n.
	Si n==2, la solución se situará en 5+2=7, y así con todos los posibles valores de n. 
	*/
	private HashMap<Integer, Double> results= loadProblems();
	
	
	//Ejecuta el problema con los parámetros introducidos por parámetro y devuelve el resultado.
	private double test(int N, int M, int generations, double occupationRatio, double broodingRatio, int survivingAttempts,
			double arRatio, double depredationPercentage, double depredationProbability, 
			int mutationType, int crossType, int problem, int n){
		
		double crossProbability=1;
		double mutationProbability=1;
		
		//Algoritmos que utilizaremos para la evaluación.
		CROAlgorithm cro =new CROAlgorithm(problem,n,mutationType,crossType); //Cuando se implemente la clase, instanciarla correctamente.
	
		//Generamos una población inicial.
		ArrayList<Chromosome> population= cro.generatePopulation(N, M, occupationRatio);
		ArrayList<Chromosome> fraction;
		ArrayList<Chromosome> water;	//Simulará el agua. Se almacenarán los resultados de los cruces, por ejemplo.
		
		//Generamos uno aleatorio.
		Chromosome winner=cro.generateChromosome();
		
		//Bucle principal.
		for(int i=0; i<generations;i++){
			
			//cro.printPopulation(N,M,population);
			//System.out.println("---------------------------------");
			
			
			//Fase 1a: recogemos parte de la población para usarlo en la fase 2.
			fraction=cro.pickPopulationFraction(population, broodingRatio);
			//Fase 1b: Cruces e introducción del resultado en el agua.
			water=cro.externalSexualReproduction(fraction,crossProbability);
			//Fase 2: "mutación" (Reproducción sexual interna).
			water.addAll(cro.internalSexualReproduction(mutationProbability));
			//Fase 3:
			population=cro.putLarvaesIntoPopulation(population, water, survivingAttempts);
			//Fase 4:
			cro.asexualReproduction( population, arRatio, survivingAttempts);
			//POPULATION SE MODIFICA POR REFERENCIA!!!!!--------->CUIDADO!
			//Fase 5:
			population=cro.depredate(population, depredationPercentage, depredationProbability);
			
			//Cogemos el mejor de esa generación.
			for(int j=0; j<population.size(); j++){
				//Si ese individuo de la población de "mejor" que el actual ganador...
				if(population.get(j)!=null && winner.compareTo(population.get(j))==-1){
					winner=population.get(j).getCopy();
				}
			}
		}				
		return winner.getFitness();
	}
	
	//execNum --> Número de ejecuciones para cada configuración.
	public void doTests(int problem, int n, int execNum){
		int[] nValues={50,70,100,120,150,180,200};
		int[] mValues={50,70,100,120,150,180,200};
		int[] generationValues ={50,70,100,150,180,200,250};
		double[] occupationRatioValues= {0.1,0.3,0.5,0.7};
		double[] broodingRatioValues= {0.1,0.3,0.5};
		int [] survivingAttempts = {1,2,3,4};
		double[] arRatioValues= {0.1,0.3,0.5,0.7};
		double[] depredationRatioValues= {0.1,0.3,0.5,0.7};
		double[] depredationProbabilitiesValues= {0.1,0.3,0.5,0.7};
		int[] crossTypesValues={1,2,3,4};
		int[] mutationTypesValues={1};
		
		double bestDiff=10000;
		double currentDiff;
		double bestValue=0;
		
		double average=0;
		
		double desviacion=0;
		double varianza=0;
		
		int bestNValue=0;
		int bestMValue=0;
		int bestGenerationValue=0;
		double bestOccupationRatio=0;
		double bestBroodingRatio=0;
		double bestSurvivingAttemps=0;
		double bestArRatio=0;
		double bestDepredationRatio=0;
		double bestDepredationProb=0;
		double bestCrossType=0;
		double bestMutationType=0;
		
		double current;
		
		for(int nIndex=0; nIndex<nValues.length;nIndex++){
			for(int mIndex=0; mIndex<mValues.length; mIndex++){
				for(int genIndex=0; genIndex<generationValues.length; genIndex++){
					for(int orIndex=0; orIndex<occupationRatioValues.length; orIndex++){
						for(int brIndex=0; brIndex<broodingRatioValues.length;brIndex++){
							for(int saIndex=0; saIndex<survivingAttempts.length;saIndex++ ){
								for(int arIndex=0; arIndex<arRatioValues.length; arIndex++){
									for(int drIndex=0; drIndex<depredationRatioValues.length; drIndex++){
										for(int dpIndex=0; dpIndex<depredationProbabilitiesValues.length; dpIndex++){
											for(int ctIndex=0; ctIndex<crossTypesValues.length; ctIndex++){
												for(int mtIndex=0; mtIndex<mutationTypesValues.length; mtIndex++){
	
													average=0;
													bestDiff=1000;
													desviacion=0;
													
													for(int i=0; i<execNum; i++){
														//Devuelve el resultado del cromosoma con mayor fitness.
														current= this.test(nValues[nIndex], mValues[mIndex], generationValues[genIndex], occupationRatioValues[orIndex],
																broodingRatioValues[brIndex],survivingAttempts[saIndex], arRatioValues[arIndex],  arRatioValues[arIndex],
																depredationProbabilitiesValues[dpIndex], mutationTypesValues[mtIndex], crossTypesValues[ctIndex], problem, n );
														
														//Si la diferencia con la respuesta correcta es menor que la mejor respuesta obtenida hasta ahora, almacenamos el resultado.
														
														average+=current;
														desviacion+=Math.pow(current, 2);
														
														if(problem!=5){
															currentDiff=Math.abs(results.get(problem+n)-current);
														}else{
															currentDiff=Math.abs(results.get(problem)-current);
														}
														
														if(currentDiff<bestDiff){
															bestDiff=currentDiff;
															bestValue=current;
														}
													}
													
													//Calculamos y moostramos los resultados.
													average=average/execNum;		//Calculamos la media.
													varianza=(desviacion/execNum)-Math.pow(average,2);	//Calculamos la varianza.
													desviacion= Math.sqrt(varianza);					//Calculamos la desviación
													
													System.out.println("***********************************************************");
													System.out.println("***********************************************************");
													System.out.println("***********************************************************");

													System.out.println("Parámetros utilizados: ");
													
													System.out.println("-N: "+nValues[nIndex]);
													System.out.println("-M: "+mValues[mIndex]);
													System.out.println("-Generaciones: "+ generationValues[genIndex]);
													System.out.println("-Ratio de ocupación: "+ occupationRatioValues[orIndex]);
													System.out.println("-de Ratio de brooging: "+broodingRatioValues[brIndex]);
													System.out.println("-Intentos de supervivencia: "+survivingAttempts[saIndex]);
													System.out.println("-Ratio de Reproducción asexual: "+arRatioValues[arIndex]);
													System.out.println("-Ratio de 'Depredation' :"+depredationRatioValues[arIndex]);
													System.out.println("-Probabilidad de 'Depredation': "+depredationProbabilitiesValues[dpIndex]);
													System.out.println("-Tipo de cruce: "+mutationTypesValues[mtIndex]);
													System.out.println("-Tipo de mutación: "+crossTypesValues[ctIndex]);
													
													System.out.println("Resultados ( "+ execNum+" ejecuciones): ");
													System.out.println("-Mejor solución: "+bestValue);
													System.out.println("-Media de soluciones: "+average);
													System.out.println("-Desviación típica de soluciones: "+desviacion);
													System.out.println("-Varianza en las soluciones: "+varianza);


												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	//n puede ir de 1 a 4.
	private static HashMap<Integer, Double> loadProblems(){
		HashMap<Integer, Double> result= new HashMap<Integer, Double>();
		result.put(1, 22.3136);
		result.put(2, -959.6407);
		result.put(3, 38.809);
		result.put(4, -186.7309);
		result.put(6, (double) (-1)); //Problema 5 y n=1.
		result.put(7, -1.959091);
		result.put(8, -2.897553);
		result.put(9, -3.886358);	//Problema 5 y n=4.
		
		return result;
	}	
	
	public static void main(String[] args){
		TestCases tc= new TestCases();
		
		tc.doTests(1, 0, 20);
	}
	
}
