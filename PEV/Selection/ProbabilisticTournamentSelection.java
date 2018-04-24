package Selection;

import Population.Chromosome;

public class ProbabilisticTournamentSelection extends SelectionAlgorithm{
	private int Z;
	private double P;
	public ProbabilisticTournamentSelection(int Z, int P){
		super();
		this.Z=Z;
		this.P=(double) P/100;
	}

	@Override
	public Chromosome[] select(int N, Chromosome[] population) throws Exception{
		// DetOrProb es para diferenciar determinstico de probabilistico y Z son los individuos de la subseleccion
		if(N> population.length) throw new Exception();
		if(Z> population.length) throw new Exception();
		
		Chromosome[] selected= new Chromosome[N];
		double[] acumulated_probability= new double[population.length];
		acumulated_probability= calculate_acumulated_probability(population);
		//Generamos 1 numero aleatorio(primera marca) y seleccionamos N individuos.
		
		for(int i=0; i<N ; i++){
			double currentBestFitness=0; // indice del fitness del mejor elemento de la subseleccion por el momento
			int currentBestElement=0;// indice del mejor elemento de la subseleccion por el momento
			// para determinar el criterio de seleccion de cada elemento respecto al valor de P
			double rand;
			rand=this.generate_random();// numero 0..1
			currentBestFitness = (rand>P)?0:10000;
			
			for(int j=0;j<Z;j++){
				int random;
				random=this.generate_random3(population.length);
				//P a de ser del 50% al 100%
				if (rand>P && acumulated_probability[random]> currentBestFitness){
					currentBestElement=random;
					currentBestFitness=acumulated_probability[random];
				}else if(rand<P && acumulated_probability[random]< currentBestFitness){
						currentBestElement=random;
						currentBestFitness=acumulated_probability[random];
				}
				
						
			  }
					
			  selected[i]= population[currentBestElement];
			
		   }
		return selected;
	}
}
	
	
