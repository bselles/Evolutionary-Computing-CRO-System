package Population;

public abstract class Chromosome implements Comparable<Chromosome>{
	/**************************************************************************
	 * ATRIBUTOS
	 **************************************************************************/
	protected Gen[] genotype;		//El n�mero de genes encaja con el n�mero de variables que tratamos en el problema.
	protected boolean maximizacion;	//Determina si el problema es de maximizaci�n o minimizaci�n.
	protected double fitness;		//Fitness asociado al cromosoma.
	
	/**************************************************************************
	 * M�TODOS PRIVADOS
	 **************************************************************************/
	//Cada cromosoma, en funci�n del problema que represente,calcular� el fitness de una forma u otra.
	protected abstract double calculateFitness();	
	
	/**************************************************************************
	 * M�TODOS P�BLICOS
	 **************************************************************************/
	public abstract Chromosome getCopy();
	
	public double getFitness(){
		return this.fitness;
	}

	//Devuelve el n�mero de genes (variables) que tiene el cromosoma en cuestion.
	public int getNumGens(){
		return this.genotype.length;
	}
	
	//Devuelve el gen asociado a la posici�n i.
	//DEVOLVEMOS UNA COPIA EFECTIVA!!!
	public Gen getGen(int i){
		return this.genotype[i].getCopy();
	}
	
	public void setGen(int i, Gen g){
		this.genotype[i]=g.getCopy(); //Se hace una copia efectiva por seguridad.
		//Despu�s de actualizar el genotipo, actualizamos el fitness.
		this.fitness=this.calculateFitness();
	}
	
	public boolean getMaximizacion(){
		return this.maximizacion;
	}
	
	/*Para comparar distintos cromosomas en funci�n del valor de su funci�n de 
	fitness y el tipo de problema.*/
	public int compareTo(Chromosome other){
		if(this.maximizacion){
			if(this.getFitness()>other.getFitness()) return 1;
			else if(this.getFitness()==other.getFitness()) return 0;
			else return -1;
		}else{
			if(this.getFitness()>other.getFitness()) return -1;
			else if(this.getFitness()==other.getFitness()) return 0;
			else return 1;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		if( ! ((Chromosome)obj).genotype.equals(this.genotype) )
			return false;
		return true;
	}
	
	//PARA DEBUG
	//Devuelve un string con la informaci�n relevante del cromosoma.
	@Override
	public String toString(){
		String result="";
		result=result+ "***************************************************\n";
		result=result+ "Chromosome info:\n";
		result=result+ "Num gens: "+ this.genotype.length +"\n";
		for(int i=0; i<this.genotype.length; i++){
			result=result+ "---------------------Gen "+ (i+1) + "---------------------\n";
			result=result+ genotype[i].toString();
			result=result+"\n";
		}		
		result=result+"\n";
		result=result+ "Fitness Value:"+ this.getFitness()+ "\n";
		result=result+ "***************************************************\n";
		return result;
	}
	

	
}