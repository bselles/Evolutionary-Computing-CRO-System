package Population;


/*
 * Implementación del cromosoma (posible solución) para el problema 3.
 */

public class ChromosomeP3 extends Chromosome{
	public ChromosomeP3(){
		
	}
	public ChromosomeP3(double[] xmin, double[] xmax, double TOL,boolean maximizacion){
		super();
		this.genotype=new BinaryGen[xmin.length];
		//Crearemos tantos genes como elementos haya en xmin|xmax|TOL (2).
		for (int i=0; i<xmin.length; i++){
			this.genotype[i]= new BinaryGen(xmin[i], xmax[i],TOL);
		}
		this.maximizacion=maximizacion;

	}

	public double getFitness() {
		return 21.5 +
				this.genotype[0].getPhenotype()*
				Math.sin(4*Math.PI*this.genotype[0].getPhenotype())+
				this.genotype[1].getPhenotype()*
				Math.sin(20*Math.PI*this.genotype[1].getPhenotype());
	}
	
	@Override
	public Chromosome getCopy() {
		Chromosome result= new ChromosomeP3();
		result.maximizacion=this.maximizacion;
		result.genotype=new BinaryGen[this.genotype.length];
		for(int i=0;i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].copyGen();
		}
		return result;
	}

}
