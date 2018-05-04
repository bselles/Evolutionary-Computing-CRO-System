package Algorithms;

import java.util.ArrayList;

import Mutation.RealMutation;
import Population.Chromosome;
import Population.ChromosomeP1;
import Population.ChromosomeP2;
import Population.ChromosomeP3;
import Population.ChromosomeP4;
import Population.ChromosomeP5;
import Reproduction.RealReprod;

public class CROAlgorithm {
	
	private int problem;
	private int n;
	private int mutationType;
	private int crossType;
	
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
		ArrayList<Chromosome> result = new ArrayList<Chromosome>();
		this.internalSelecteds =  new ArrayList<Chromosome>();
		
		for(int i=0; i<population.size();i++){
			if(population.get(i) != null){
				if(Math.random()>0.5){
					result.add(population.get(i));
				}else{
					this.internalSelecteds.add(population.get(i));
				}
			}
		}
		
		//Distribuimos.

		int n= (int) (Math.floor(this.internalSelecteds.size()+result.size())*fraction);
		
		while(n != result.size()){
			if(n > result.size()){
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
	public  ArrayList<Chromosome> externalSexualReproduction(ArrayList<Chromosome> population, double crossProb){
		ArrayList<Chromosome> resul = new ArrayList<>();
		resul.ensureCapacity(population.size());
		
		for(int i = 0; i < population.size()-1; i++) 
			resul.addAll(RealReprod.reproduce(population.get(i), population.get(i+1), crossProb, this.crossType));
		
		if(population.size()%2 == 1)
			resul.add(population.get(population.size()-1));
		
		return resul;
	}

	/*
	 * Representa la fase 2. Cada uno disemina una mutación al mar.
	 */
	public ArrayList<Chromosome> internalSexualReproduction(ArrayList<Chromosome> population, double mutationProbability){

		ArrayList<Chromosome> resul = new ArrayList<>();
		resul.ensureCapacity(population.size());
		for(int i = 0; i < population.size()-1; i++) resul.add(RealMutation.mutate(population.get(i), this.mutationType, mutationProbability, 1));
		
		return resul;
	}

	
	/*
	 * Representa la fase 3.
	 * Las larvas generadas en la fase 1 y 2, que están en el agua, luchan por situarse en el coral (population).
	 * 
	 */
	public void putLarvaesIntoPopulation(ArrayList<Chromosome> population, ArrayList<Chromosome> water, int survivingAttempts){

	}

	/*
	 * Representa la fase 4. 
	 * Reproducción asexual.
	 */
	public void asexualReproduction(ArrayList<Chromosome> population, double ratio, int survivingAttempts){
		int clones = (int) (population.size() * ratio), pos;
		
		for(int i = 0; i < clones; i++) {
			do { pos = generateRandomInt(0, population.size());
			} while( population.get(pos) == null );
			
			Chromosome aux = population.get(pos).getCopy();
			
			for(int j=0; j<survivingAttempts; j++) {
				pos = generateRandomInt(0, population.size());
				if(population.get(pos) == null || population.get(pos).getFitness() < aux.getFitness()) {
					population.set(pos, aux);
					break;
				}
			}
			
		}
		
	}

	/*
	 * Representa la fase 5.
	 * Se eliminan ciertos individuos de la población (depredation).
	 */
	public void depredate(ArrayList<Chromosome> population, double ratio, double probability){
		
	}


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
		
		int n=5;
		int m=5;
		
		ArrayList<Chromosome> result=al.generatePopulation(n, m, 0.5);
		
		int j=0; 
		for(int i=0; i<n*m; i++){
			if(result.get(i)==null){
				System.out.print(" - ");
			}else{
				System.out.print(" X ");
			}
			j++;
			if(j==m){
				j=0;
				System.out.println("");
			}
		}
		
		
		System.out.println("");
		
		j=0; 
		for(int i=0; i<n*m; i++){
			if(result.get(i)==null){
				System.out.print(" - ");
			}else{
				System.out.print(" X ");
			}
			j++;
			if(j==m){
				j=0;
				System.out.println("");
			}
		}
	}
}