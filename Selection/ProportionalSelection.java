package Selection;

import Population.Chromosome;

public class ProportionalSelection extends SelectionAlgorithm{
	public ProportionalSelection(){
		super();
	}

	@Override
	public Chromosome[] select(int N, Chromosome[] population) throws Exception{
		
		if(N> population.length) throw new Exception();
		
		Chromosome[] selected= new Chromosome[N];
		double[] acumulated_probability= new double[population.length];
		acumulated_probability= calculate_acumulated_probability(population);
		double random;
		//Generamos N numeros aleatorios y seleccionamos N individuos.
		
		for(int i=0; i<N ; i++){
			random=this.generate_random();
			
			int indice=pick_from_roullete(random, acumulated_probability,0,population.length);
			selected[i]= population[indice].getCopy();
		}		
		
		return selected;
	}
}