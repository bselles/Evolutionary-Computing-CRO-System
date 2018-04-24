package Population;

/*
 * Implementación del cromosoma (posible solución) para el problema 4.
 */

public class ChromosomeP4 extends Chromosome{
	
	public ChromosomeP4(){	
	}
	
	public ChromosomeP4(int xmin, int xmax, double TOL, boolean maximizacion){
		super();
		this.genotype=new BinaryGen[2];
		for (int i=0; i<2; i++){
			this.genotype[i]= new BinaryGen(xmin, xmax,TOL);
		}
		this.maximizacion=maximizacion;

	}
	
	public double getFitness(){
		double result1=0, result2=0;
		for (int i=1; i<=5; i++){
			result1=result1+i*Math.cos((i+1)*this.genotype[0].getPhenotype()+i);
		}
		for (int i=0; i<=5; i++){
			result2=result2+i*Math.cos((i+1)*this.genotype[1].getPhenotype()+i);			
		}
		return result1*result2;
	}	
	

	@Override
	public Chromosome getCopy() {
		Chromosome result= new ChromosomeP4();
		result.maximizacion=this.maximizacion;
		result.genotype=new BinaryGen[this.genotype.length];
		for(int i=0;i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].copyGen();
		}
		return result;
	}
}
