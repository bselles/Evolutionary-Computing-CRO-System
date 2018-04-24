package Selection;

import Population.Chromosome;

public class TruncateSelection extends SelectionAlgorithm{
	private double trunc;
	public TruncateSelection(int trunc){
		super();
		this.trunc= (double) trunc;
	}
	
	//La población ya vendrá ordenada de peor a mejor fitness.
	@Override 
	public Chromosome[] select(int N, Chromosome[] population) throws Exception{
		
		if(N> population.length) throw new Exception();
		
		Chromosome[] selected= new Chromosome[N];
	
		int subgrupo= (int)(population.length * (this.trunc/100));
		int i=0;
		while(i<N){
			for(int j=0;i<N&& j<subgrupo;j++){
				selected[i]=population[N-1-j];
				i++;		
			}
		}
		
		return selected;
	}
}