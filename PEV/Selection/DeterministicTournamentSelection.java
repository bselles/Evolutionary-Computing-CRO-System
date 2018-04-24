package Selection;

import Population.Chromosome;


public class DeterministicTournamentSelection extends SelectionAlgorithm{
	private int Z;
	public DeterministicTournamentSelection(int Z){
		super();
		this.Z=Z;
	}

	@Override
	public Chromosome[] select(int N, Chromosome[] population) throws Exception{
		// DetOrProb es para diferenciar determinstico de probabilistico y Z son los individuos de la subseleccion
		if(N> population.length) throw new Exception();
		if(Z> population.length) throw new Exception();
		
		Chromosome[] selected= new Chromosome[N];
		double[] acumulated_probability= new double[population.length];
		acumulated_probability= calculate_acumulated_probability(population);
		
		for(int i=0; i<N ; i++){
			double currentBestFitness=0;//fitness del mejor elemento de la subseleccion por el momento
			int currentBestElement=0;// indice del mejor elemento de la subseleccion por el momento
			for(int j=0;j<Z;j++){
				int random;
				random=this.generate_random3(population.length);
				
				if (acumulated_probability[random]> currentBestFitness){
					currentBestElement=random;
					currentBestFitness=acumulated_probability[random];
				}
				
						
			}
			selected[i]= population[currentBestElement];	
			
		}
		
		return selected;
	}	
}