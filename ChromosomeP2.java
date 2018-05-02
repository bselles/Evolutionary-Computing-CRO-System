package Population;

/*
 * Implementaci�n del cromosoma (posible soluci�n) para el problema 2.
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
		this.maximizacion=false;	//Problema de minimizaci�n.

	}
	
	/**************************************************************************
	 * M�TODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		//En genotype[1] estar� x2 y en genotype[0] estar� x1.
		return -((this.genotype[1].getPhenotype()+47)*
				Math.sin(Math.sqrt(Math.abs(this.genotype[1].getPhenotype()+ 
						(this.genotype[0].getPhenotype()/2)+47))))-
						this.genotype[0].getPhenotype()*
						Math.sin(Math.sqrt(Math.abs(this.genotype[0].getPhenotype()-
								(this.genotype[1].getPhenotype()+47))));
	}	
	/**************************************************************************
	 * M�TODOS P�BLICOS
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
	
	public Chromosome mutation(int tipo, double prob, double desviacion){
		
		RealGen genAux;
		Chromosome a = this.getCopy();
		
		switch (tipo) {
		case 1: //Mutacion uniforme
			for (int i =0; i< this.getNumGens(); i++ ) {
				if(Math.random() < prob){
					
					genAux = (RealGen) this.getGen(i).getCopy();
					genAux.setPhenotype(  -512 + Math.random() * 1024);
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
					 if(res > 512)genAux.setPhenotype(512);
					 else genAux.setPhenotype(res);
				}
				else{
					 res = calculazacum(x) - genAux.getPhenotype();
					 if(res < -512)genAux.setPhenotype(-512);
					 else genAux.setPhenotype(res);
				}

				a.setGen(i, genAux);	

			}
			
			break;
			
		

		}
		
		return a;

	}
}

