package Population;

/*
 * Implementación del cromosoma (posible solución) para el problema 1.
 */

public class ChromosomeP1 extends Chromosome {
	
	public ChromosomeP1(){		
	}
	
	public ChromosomeP1(int xmin, int xmax, double TOL, boolean maximizacion){
		super();
		this.genotype=new BinaryGen[1];
		//Como en el problema 1 solo hay una variable, solo tendrá un gen.
		this.genotype[0]= new BinaryGen(xmin, xmax,TOL);
		this.maximizacion=maximizacion;
	}
	

	public  double getFitness() {
		return 20+Math.E-20*Math.pow(Math.E, -0.2*Math.abs(this.genotype[0].getPhenotype()))-
				Math.pow(Math.E,Math.cos(2*Math.PI*this.genotype[0].getPhenotype()));
	}	
		


	@Override
	public Chromosome getCopy() {
		Chromosome result= new ChromosomeP1();
		result.maximizacion=this.maximizacion;
		result.genotype=new BinaryGen[this.genotype.length];
		for(int i=0;i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].copyGen();
		}
		return result;
	}

}
