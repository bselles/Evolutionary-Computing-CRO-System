package Population;


/*
 * Implementación del cromosoma (posible solución) para el problema 3.
 */

public class ChromosomeP3 extends Chromosome{

	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP3(){}
	
	public ChromosomeP3(double TOL){
		super();
		//Crearemos dos genes, tantas como las variables del problema.
		this.genotype=new RealGen[2];
		this.genotype[0]= new RealGen(-3, 12.1,TOL);
		this.genotype[1]= new RealGen(4.1, 5.8,TOL);
		this.maximizacion=true;
	}
	
	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		//En genotype[0] tendremos x1 y en genotype[1] x2.
		return 21.5 +
				this.genotype[0].getPhenotype()*
				Math.sin(4*Math.PI*this.genotype[0].getPhenotype())+
				this.genotype[1].getPhenotype()*
				Math.sin(20*Math.PI*this.genotype[1].getPhenotype());
	}
	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	public Chromosome getCopy(){
		Chromosome result= new ChromosomeP3();
		result.maximizacion=this.maximizacion;
		result.genotype= new Gen[this.genotype.length];
		for(int i=0; i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].getCopy();
		}
		result.fitness=this.fitness;
		return result;
	}
}
