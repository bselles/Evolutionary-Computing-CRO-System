package Population;


/*
 * Implementación del cromosoma (posible solución) para el problema 2.
 */


public class ChromosomeP2 extends Chromosome {
	
	public ChromosomeP2(){		
	}
	public ChromosomeP2(double xmin, double xmax, double TOL, boolean maximizacion){
		super();
		this.genotype=new BinaryGen[2];
		//Crearemos dos genes, tantas como las variables del problema.
		for (int i=0; i<2; i++){
			this.genotype[i]= new BinaryGen(xmin, xmax,TOL);
		}
		this.maximizacion=maximizacion;

	}
	
	public double getFitness() {
		return -((this.genotype[1].getPhenotype()+47)*
				Math.sin(Math.sqrt(Math.abs(this.genotype[1].getPhenotype()+ 
						(this.genotype[0].getPhenotype()/2)+47))))-
						this.genotype[0].getPhenotype()*
						Math.sin(Math.sqrt(Math.abs(this.genotype[0].getPhenotype()-
								(this.genotype[1].getPhenotype()+47))));
	}	
	
	
	@Override
	public Chromosome getCopy() {
		Chromosome result= new ChromosomeP2();
		result.maximizacion=this.maximizacion;
		result.genotype=new BinaryGen[this.genotype.length];
		for(int i=0;i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].copyGen();
		}
		return result;
	}
	
}

