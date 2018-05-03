package Population;

/*
 * Implementación del cromosoma (posible solución) para el problema 2.
 */

public class ChromosomeP2 extends Chromosome {
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP2(){};
	
	public ChromosomeP2(double TOL){
		super();
		this.genotype=new RealGen[2];
		//Crearemos dos genes, tantas como las variables del problema.
		this.genotype[0]= new RealGen(-512, 512,TOL);
		this.genotype[1]= new RealGen(-512, 512,TOL);
		this.maximizacion=false;	//Problema de minimización.
		this.fitness=calculateFitness();

	}
	
	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		//En genotype[1] estará x2 y en genotype[0] estará x1.
		return -((this.genotype[1].getPhenotype()+47)*
				Math.sin(Math.sqrt(Math.abs(this.genotype[1].getPhenotype()+ 
						(this.genotype[0].getPhenotype()/2)+47))))-
						this.genotype[0].getPhenotype()*
						Math.sin(Math.sqrt(Math.abs(this.genotype[0].getPhenotype()-
								(this.genotype[1].getPhenotype()+47))));
	}	
	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	public Chromosome getCopy(){
		Chromosome result= new ChromosomeP2();
		result.maximizacion=this.maximizacion;
		result.genotype= new Gen[this.genotype.length];
		for(int i=0; i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].getCopy();
		}
		result.fitness=this.fitness;
		return result;
	}
}

