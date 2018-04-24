package Selection;

import java.util.Random;

import Population.Chromosome;

/*
 * Clase abstracta que representa los distintos algoritmos de selección.
 * Toda implementación de los algoritmos de selección deberá heredar de esta clase.
 * 
 */
public abstract class SelectionAlgorithm{
	public abstract Chromosome[] select(int N, Chromosome[] population) throws Exception;
	
	
	protected double[] calculate_acumulated_probability(Chromosome[] population){	
		double acumulated=0;
		double[] result= new double[population.length];
		
		
		double aux=0;
		double[] max_min = new double[2];
		// saca el maximo y el minimo de la poblacion
		max_min=max_min(population);
		//calculamos el fitnes total 
		double total_fitness= get_total_fitness(population,max_min[0],max_min[1]);
		
		for( int i=0; i<population.length; i++){
			
			aux=population[i].getFitness();
			//adapta el valor dependiendo del tipo de problema para facilitar los calculos
			aux=desplazamiento(population,aux,max_min[0],max_min[1]);
			acumulated=(aux/total_fitness)+acumulated;
			
			result[i]=acumulated;
		}
		return result;
	}
	// adapta el valor dependiendo de si estamos en maximizacion o en minimizacion
	protected double desplazamiento(Chromosome[] population,double aux, double max , double min){
		double cambiado=0;
		if(!population[0].maximizacion){// estamos minimizando , hay que asegurarse de transformar 
			cambiado = max-aux;
			
			
		}else{ // si estamos en maximizacion , para evitar valores negativos 
			cambiado = aux + Math.abs(min);
			
		}
		return cambiado;
	}
	
	// obtiene el mayor y el menor numero de una poblacion
	protected double[] max_min(Chromosome[] population){
		double[] max_min=new double[2];
		 max_min[0] = -1000000;
		 max_min[1]=1000000;
		
		// saca el maximo y el minimo , dependiendo de si estamos maximizando o no , nos sera util uno u otro
			for( int j=0; j<population.length; j++){
				if(population[j].getFitness()>max_min[0]){
					max_min[0]=population[j].getFitness();
				}
				if(population[j].getFitness()<max_min[1]){
					max_min[1]=population[j].getFitness();
				}
			}
			max_min[0]=max_min[0]*1.05;// para evitar subadaptacion 0
			max_min[1]=max_min[1]*1.05;
			
			return max_min;
	}

	protected double generate_random(){
		Random r = new Random();
		return r.nextDouble();
	}
	protected double generate_random2(int N){ // calcula decimal aleatorio entre 0 y 1/N
		Random r = new Random();
		double randomValue = 0 + ((1/(double)N) - 0) * r.nextDouble();
		return randomValue;
	}
	protected int generate_random3(int n){// calcula aletorio entre 0 y n
		Random r = new Random();
		return r.nextInt(n);
	}
	
	//BÃºsqueda binaria recursiva.
	protected int pick_from_roullete(double rand, double[] acumulated_probability, int min, int max){
		
		int index= (min+max)/2;	
		double valorMedio = acumulated_probability[index];
		
		if(min>max){
			if(acumulated_probability[0]>rand)return 0;
			
			return index+1;
		}else if( valorMedio ==rand){
			return index;
		}else if( valorMedio < rand ){
			return pick_from_roullete(rand,acumulated_probability,index+1,max);
		}else if(valorMedio > rand){
			return pick_from_roullete(rand,acumulated_probability,min,index-1);
		}else{
			return -1;
		}
	}
	
	private double get_total_fitness(Chromosome[] population, double max,double min){
		double result=0;
		double aux=0;
		
		
		for (int i=0; i<population.length; i++){
			aux=population[i].getFitness();
			
			aux=desplazamiento(population,aux,max,min);
			
			result +=  aux;
		}
		return result;
	}
	
}