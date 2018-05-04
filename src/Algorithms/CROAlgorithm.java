package Algorithms;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public void printPopulation(int n, int m, ArrayList<Chromosome> population){
		int j=0;
		for(int i=0; i<n*m; i++){
			if(population.get(i)==null){
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

	public Chromosome  generateChromosome(){
		return this.createChromosome();
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
		//El resultado serán los que harán la fase 1b : reproduccón sexual externa.
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
	public ArrayList<Chromosome> internalSexualReproduction(double mutationProbability){

		ArrayList<Chromosome> resul = new ArrayList<>();
		//resul.ensureCapacity(internalSelecteds.size());
		for(int i = 0; i < internalSelecteds.size()-1; i++) resul.add(RealMutation.mutate(internalSelecteds.get(i), this.mutationType, mutationProbability, 1));
		
		return resul;
	}

	
	/*
	 * Representa la fase 3.
	 * Las larvas generadas en la fase 1 y 2, que están en el agua, luchan por situarse en el coral (population).
	 * 
	 */
	public ArrayList<Chromosome> putLarvaesIntoPopulation(ArrayList<Chromosome> population, ArrayList<Chromosome> water, int survivingAttempts){
		
		//Clonamos la población inicial.
		
		ArrayList<Chromosome> result= (ArrayList<Chromosome>) population.clone();
		
		int counter=0;
		int pos;
		
		
		for(int i=0; i<water.size(); i++){
			counter=0;
			do{
				pos=generateRandomInt(0,result.size());
				counter++;
				//Mientras la posición este ocupada y el fitness el elemento de esta posición es
				//mejor que el fitness del que queremos introducir.
			}while(result.get(pos)!=null && (result.get(pos).compareTo(water.get(i))==1)&& counter<survivingAttempts);
			
			//Insertamos la solución, si procede.
			if(counter<survivingAttempts){
				result.set(pos, water.get(i));
			}
		
		}
		
		return result;
	}

	
	/****
	CAMBIAR: ELIMINAR EL PASO POR REFERENCIA
	CAMBIAR COMPARACIÓN FITNESS!!!!!!  ------------------->DONE
	****/
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
				if(population.get(pos) == null || aux.compareTo(population.get(pos))==1) {
				//if(population.get(pos) == null || population.get(pos).getFitness() < aux.getFitness()) {
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
	public ArrayList<Chromosome> depredate(ArrayList<Chromosome> population, double ratio, double probability){
		ArrayList<Chromosome> result= (ArrayList<Chromosome>) population.clone();
		
		//Cogemos los individuos del arrecife y obtenemos los peores.
		ArrayList<Chromosome> sorted= new ArrayList<Chromosome>();
		
		for(int i=0; i<result.size();i++){
			if(result.get(i)!=null) sorted.add(result.get(i));
		}

		//Los ordenamos y eliminamos los n peores.
		Collections.sort(sorted);

		int n=(int) Math.floor(ratio*sorted.size());

		for(int i=0; i<n; i++){
			sorted.remove(i);
		}
		
		//Recorremos result y eliminamos los que no se encuentren en sorted.
		for(int i=0; i<result.size();i++){
			if(result.get(i)!=null && !sorted.contains(result.get(i))){
				//En función de la probabilidad introducido por parámetro, lo eliminamos o no.
				if(generateRandomDouble(0,1)<probability){
					result.set(i,null);	//Eliminarlo implica ponerlo a null.
				}
			}
		}
		
		return result;		
	}


	private int generateRandomInt(int min, int max){
		return (int) Math.floor(Math.random()*(max-min)+min);
	}
	
	
	private double generateRandomDouble(double min, double max){
		return  Math.floor(Math.random()*(max-min)+min);
	}
	
	private Chromosome createChromosome(){
		switch (this.problem){
		case 1:
			return new ChromosomeP1();
		case 2:
			return new ChromosomeP2();
		case 3:
			return new ChromosomeP3();
		case 4:
			return new ChromosomeP4();
		case 5:
			return new ChromosomeP5(this.n);
		default:
			System.out.println("No debería entrar aquí. Número de problema erroneo.");
			return null;
		}
	}
	
	public static void main(String[] args){
		
		CROAlgorithm al= new CROAlgorithm(1,1,1,1);
		
		int n=3;
		int m=3;
		
		ArrayList<Chromosome> result=al.generatePopulation(n, m, 0.3);
		
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
		
		result=al.depredate(result, 0.5, 1);
		
		System.out.println("-----------------");
		
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