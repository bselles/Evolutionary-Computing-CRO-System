package Selection;

import Population.Chromosome;

public class StochasticSelection extends SelectionAlgorithm{
	public StochasticSelection(){
		super();
	}

	@Override
	public Chromosome[] select(int N, Chromosome[] population) throws Exception{
		
		if(N> population.length) throw new Exception();
		
		Chromosome[] selected= new Chromosome[N];
		double[] acumulated_probability= new double[population.length];
		acumulated_probability= calculate_acumulated_probability(population);
		//Generamos 1 numero aleatorio(primera marca) y seleccionamos N individuos.
		double random;
		random=this.generate_random2(N);// 0 a 1/N
		for(int i=0; i<N ; i++){
			int indice=pick_from_roullete(random, acumulated_probability,0,population.length);
			selected[i]= population[indice];
			

			random += (1/(double)N) ; // la distancia entre marcas siempre es 1/N de forma constante

			
		}
		
		return selected;
	}	
}