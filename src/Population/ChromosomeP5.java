package Population;

public class ChromosomeP5 extends Chromosome{
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP5(){}
	
	public ChromosomeP5(double TOL, int n){
		super();
		this.genotype=new RealGen[n];
		//Crearemos tantos genes como variables tenga el problema.
		for (int i=0; i<n; i++){
			this.genotype[i]= new RealGen(0, Math.PI,TOL);
		}
		this.maximizacion=false; //Problema de minimización.
		this.fitness=calculateFitness();

	}

	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		double result=0;		
		for(int i=0;i<this.genotype.length; i++){
			result=result+Math.sin(this.genotype[i].getPhenotype())*
			Math.pow(Math.sin((i+1)*(Math.pow(this.genotype[i].getPhenotype(),2)/Math.PI)), 20);					
		}	
		return -result;
	}	
	
	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	public Chromosome getCopy(){
		Chromosome result= new ChromosomeP5();
		result.maximizacion=this.maximizacion;
		result.genotype= new Gen[this.genotype.length];
		for(int i=0; i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].getCopy();
		}
		result.fitness=this.fitness;
		return result;
	}
}