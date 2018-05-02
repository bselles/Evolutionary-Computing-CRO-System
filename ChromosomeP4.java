package Population;

/*
 * Implementación del cromosoma (posible solución) para el problema 4.
 */

public class ChromosomeP4 extends Chromosome{
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP4(){}
	
	public ChromosomeP4(double TOL){
		super();
		//Crearemos dos genes, tantas como las variables del problema.
		this.genotype=new RealGen[2];
		this.genotype[0]= new RealGen(-10, 10,TOL);
		this.genotype[1]= new RealGen(-10, 10,TOL);
		this.maximizacion= false;	//Problema de minimización.

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
	
	public Chromosome mutation(int tipo, double prob, double desviacion){
		
		RealGen genAux;
		Chromosome a = this.getCopy();
		
		switch (tipo) {
		case 1: //Mutacion uniforme
			for (int i =0; i< this.getNumGens(); i++ ) {
				if(Math.random() < prob){
					
					genAux = (RealGen) this.getGen(i).getCopy();
					genAux.setPhenotype(  -10 + Math.random() * 20);
					a.setGen(i, genAux);
					
				}
			}
				
					
			break;

		case 2: //Mutacion no uniforme
			double x = Math.random() * 3, res;		
			int rand;
			
			
			if(Math.random() > prob)  //no se cruzan
				break;
			for (int i =0; i< this.getNumGens(); i++ ) { //Cuidado dominio de la funcion
			
				rand = (int) Math.random();
				
				genAux = (RealGen) this.getGen(i).getCopy();
				if(rand == 1){
					 res = calculazacum(x) + genAux.getPhenotype();
					 if(res > 10)genAux.setPhenotype(10);
					 else genAux.setPhenotype(res);
				}
				else{
					 res = calculazacum(x) - genAux.getPhenotype();
					 if(res < -10)genAux.setPhenotype(-10);
					 else genAux.setPhenotype(res);
				}

				a.setGen(i, genAux);	

			}
			
			break;
			
		

		}
		
		return a;

	}
}
