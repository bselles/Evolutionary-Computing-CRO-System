package Population;


/*
 * Clase abstracta que representa un cromosoma (individuo de la población):
 * Existirá una implementación concreta para cada problema (clase que heredará de esta). 
 */
public abstract class Chromosome implements Comparable<Chromosome>{
	
	//Array de genes. Cada gen representa una de las variables del problema concreta.
	protected Gen[] genotype;
	
	//Indicará si el cromosoma forma parte de un problema de maximización o minimización.
	public boolean maximizacion;
	
	
	/*Métodos que deben implementar todas las clases que implementen un
	cromosoma concreto (para cada problema).*/
	
	
	public abstract double getFitness();

	//Dado un cromosoma, devuelve una copia idéntica de este (para evitar conflictos de referencias).
	public abstract Chromosome getCopy();
	
	//Devuelve un string con la información relevante del cromosoma.
	public String toString(){
		String result="";
		result=result+ "***************************************************\n";
		result=result+ "Chromosome info:\n";
		result=result+ "Num gens: "+ this.genotype.length +"\n";
		for(int i=0; i<this.genotype.length; i++){
			result=result+ "---------------------Gen "+ (i+1) + "---------------------\n";
			result=result+ genotype[i].toString();
		}		
		result=result+ "Fitness Value:"+ this.getFitness()+ "\n";
		result=result+ "Result: "+ this.getFitness()+ "\n";
		result=result+ "***************************************************\n";
		return result;
	}
	
	//Devuelve el número de genes (variables) que tiene el cromosoma en cuestion.
	public int getNumGens(){
		return this.genotype.length;
	}
	
	//Devuelve el gen solicitado (el gen con índice i).
	public Gen getGen(int i){
		return this.genotype[i];
	}
	
	//Modifica el gen del cromosoma. Introduce el gen g en el índice i.
	public void setGen(int i,Gen g){
		this.genotype[i]=g;
	}
	
	//Para comparar distintos cromosomas en función del valor de su función de fitness y el tipo de problema.
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
	
	
}