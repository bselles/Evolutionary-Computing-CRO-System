package Population;

/*
 * Implementaci�n del cromosoma (posible soluci�n) para el problema 1.
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
		this.maximizacion=true;	//Problema de maximizaci�n.
		this.fitness=calculateFitness();
	}
	
	/**************************************************************************
	 * M�TODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		return 20+Math.E-20*Math.pow(Math.E, -0.2*Math.abs(this.genotype[0].getPhenotype()))-
				Math.pow(Math.E,Math.cos(2*Math.PI*this.genotype[0].getPhenotype()));
	}	
	/**************************************************************************
	 * M�TODOS P�BLICOS
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

}
