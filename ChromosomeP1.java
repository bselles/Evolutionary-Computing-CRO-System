package Population;

/*
 * Implementación del cromosoma (posible solución) para el problema 1.
 */

public class ChromosomeP1 extends Chromosome {
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP1(){}
	
	public ChromosomeP1(double TOL){
		super();
		this.genotype=new RealGen[1];	//Un solo gen porque solo hay una variable.
		this.genotype[0]=new RealGen(0,32,TOL);
		this.maximizacion=true;	//Problema de maximización.
	}
	
	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		return 20+Math.E-20*Math.pow(Math.E, -0.2*Math.abs(this.genotype[0].getPhenotype()))-
				Math.pow(Math.E,Math.cos(2*Math.PI*this.genotype[0].getPhenotype()));
	}	

	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	public Chromosome getCopy(){
		Chromosome result= new ChromosomeP1();
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
			
			if(Math.random() < prob){
					
					genAux = (RealGen) this.getGen(1).getCopy();
					genAux.setPhenotype(Math.random() * 32);
					a.setGen(1, genAux);
					
			}
			
				
					
			break;

		case 2: //Mutacion no uniforme
			double x, res;		
			int rand = (int) Math.random();
			if(Math.random() > prob)  //no se cruzan
				break;

			x = Math.random() * 3;
			genAux = (RealGen) this.getGen(1).getCopy();
			if(rand == 1){
				 res = calculazacum(x) + genAux.getPhenotype();
				 if(res > 32)genAux.setPhenotype(32);
				 else genAux.setPhenotype(res);
			}
			else{
				 res = calculazacum(x) - genAux.getPhenotype();
				 if(res < 0)genAux.setPhenotype(0);
				 else genAux.setPhenotype(res);
			}

			a.setGen(1, genAux);		
			
			break;

		}
		
		return a;
	}
	


}
