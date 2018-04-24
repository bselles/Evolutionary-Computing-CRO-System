package Population;

public class ChromosomeP5 extends Chromosome{
	
	public ChromosomeP5(){	
	}
	
	public ChromosomeP5(int xmin, int xmax, double TOL, int n, boolean maximizacion){
		super();
		this.genotype=new BinaryGen[n];
		//Crearemos tantos genes como variables tenga el problema.
		for (int i=0; i<n; i++){
			this.genotype[i]= new BinaryGen(xmin, xmax,TOL);
		}
	}

	public double getFitness() {
		double result=0;		
		for(int i=0;i<this.genotype.length; i++){
			result=result+Math.sin(this.genotype[i].getPhenotype())*
			Math.pow(Math.sin((i+1)*(Math.pow(this.genotype[i].getPhenotype(),2)/Math.PI)), 20);					
		}	
		return -result;
	}	
	

	
	@Override
	public Chromosome getCopy() {
		Chromosome result= new ChromosomeP5();
		result.maximizacion=this.maximizacion;
		result.genotype=new BinaryGen[this.genotype.length];
		for(int i=0;i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].copyGen();
		}
		return result;
	}
}