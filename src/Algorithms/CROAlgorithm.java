package Algorithms;

import java.util.ArrayList;

import Population.Chromosome;
import Population.ChromosomeP1;
import Population.ChromosomeP2;
import Population.ChromosomeP3;
import Population.ChromosomeP4;
import Population.ChromosomeP5;

public class CROAlgorithm {
	
	int problem;
	int n;
	int mutationType;
	int crossType;
	
	private ArrayList<Chromosome> internalSelecteds;
	
	public CROAlgorithm (int problem,int n,int mutationType,int crossType){
		this.problem=problem;
		this.n=n;
		this.mutationType=mutationType;
		this.crossType=crossType;
	}
	
	public  ArrayList<Chromosome> generatePopulation(int N, int M, double occupationRatio){
		//Su tamaño será NxM.
		ArrayList<Chromosome> result= new ArrayList<Chromosome>();
		
		//Generamos un arrecife vacío.
		for(int i=0; i<N*M; i++){
			result.add(null);
		}
		
		//Insertamos soluciones aleatorias en el tablero.
		
		int nChroms= (int) Math.ceil(N*M*occupationRatio);
		
		int pos;
		for(int i=0; i<nChroms;i++){
			pos=generateRandomInt(0,N*M);
			while(result.get(pos)!=null){
				pos=generateRandomInt(0,N*M);
			}
			result.set(pos, createChromosome());
		}
		return result;
	}

	/*Método asociado a la parte 1a: Broadcast Spawning.
	Seleccionamos una fracción de la población total y la apartamos.
	Esta fracción se usará en la fase 2 (Brooding)*/
	public  ArrayList<Chromosome> pickPopulationFraction(ArrayList<Chromosome> population, double fraction){
		ArrayList<Chromosome> result= new ArrayList<Chromosome>();
		this.internalSelecteds=  new ArrayList<Chromosome>();
		
		for(int i=0; i<population.size();i++){
			if(population.get(i)!=null){
				if(Math.random()>0.5){
					result.add(population.get(i));
				}else{
					this.internalSelecteds.add(population.get(i));
				}
			}
		}
		
		//Distribuimos.
		
		int n= (int) (Math.floor(this.internalSelecteds.size()+result.size())*fraction);
		
		while(n!=result.size()){
			if(n>result.size()){
				result.add(this.internalSelecteds.remove(generateRandomInt(0,this.internalSelecteds.size())));
			}else{
				this.internalSelecteds.add(result.remove(generateRandomInt(0,result.size())));
			}
		}
		
		//El resultado serán los que harán brooding.
		return result;
	}

	/*
	 * Representa la fase 1b. Se reproducen por parejas y lanzan sus hijos al mar.
	 */
	//public abstract ArrayList<Chromosome> externalSexualReproduction(ArrayList<Chromosome> population);

	/*
	 * Representa la fase 2.
	 */
	//public abstract ArrayList<Chromosome> internalSexualReproduction(ArrayList<Chromosome> fraction);

	
	/*
	 * Representa la fase 3.
	 * Las larvas generadas en la fase 1 y 2, que están en el agua, luchan por situarse en el coral (population).
	 * 
	 */
	//public abstract ArrayList<Chromosome> putLarvaesIntoPopulation(int survivingAttempts,ArrayList<Chromosome> population, ArrayList<Chromosome> water);

	/*
	 * Representa la fase 4. 
	 * Reproducción asexual.
	 */
	//public abstract ArrayList<Chromosome> asexualReproduction(double ratio,ArrayList<Chromosome> population);

	/*
	 * Representa la fase 5.
	 * Se eliminan ciertos individuos de la población (depredation).
	 */
	//public abstract ArrayList<Chromosome> depredate(double ratio, double probability, ArrayList<Chromosome> population);


	private int generateRandomInt(int min, int max){
		return (int) Math.floor(Math.random()*(max-min)+min);
	}
	
	private Chromosome createChromosome(){
		switch (this.problem){
		case 1:
			return new ChromosomeP1(0);
		case 2:
			return new ChromosomeP2(0);
		case 3:
			return new ChromosomeP3(0);
		case 4:
			return new ChromosomeP4(0);
		case 5:
			return new ChromosomeP5(0, this.n);
		default:
			System.out.println("No debería entrar aquí. Número de problema erroneo.");
			return null;
		}
	}
	
	public static void main(String[] args){
		
		CROAlgorithm al= new CROAlgorithm(1,1,1,1);
		
		int n=3;
		int m=3;
		
		ArrayList<Chromosome> result=al.generatePopulation(n, m, 0.5);
		int j=0; 
		for(int i=0; i<n*m; i++){
			if(result.get(i)==null){
				System.out.print(" - ");
			}else{
				System.out.print(" X ");
			}
			j++;
			if(j==3){
				j=0;
				System.out.println("");
			}
		}
	}
}