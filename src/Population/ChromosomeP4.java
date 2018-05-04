package Population;

/*
 * Implementación del cromosoma (posible solución) para el problema 4.
 */

public class ChromosomeP4 extends Chromosome{
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP4(){
		super();
		//Crearemos dos genes, tantas como las variables del problema.
		this.genotype=new RealGen[2];
		this.genotype[0]= new RealGen(-10, 10);
		this.genotype[1]= new RealGen(-10, 10);
		this.maximizacion= false;	//Problema de minimización.
		this.fitness=calculateFitness();

	}
	
	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	public double calculateFitness(){
		double result1=0, result2=0;
		for (int i=1; i<=5; i++){
			result1=result1+i*Math.cos((i+1)*this.genotype[0].getPhenotype()+i);
		}
		for (int i=0; i<=5; i++){
			result2=result2+i*Math.cos((i+1)*this.genotype[1].getPhenotype()+i);			
		}
		return result1*result2;
	}	
	
	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	public Chromosome getCopy(){
		Chromosome result= new ChromosomeP4();
		result.maximizacion=this.maximizacion;
		result.genotype= new Gen[this.genotype.length];
		for(int i=0; i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].getCopy();
		}
		result.fitness=this.fitness;
		return result;
	}
}
