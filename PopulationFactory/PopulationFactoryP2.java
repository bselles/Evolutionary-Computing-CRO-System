package PopulationFactory;

import Population.Chromosome;
import Population.ChromosomeP2;


/*
 * Implementación de la factoria que genera individuos (posibles 
 * soluciones/cromosomas) del problema 2.
 * 
 * Contiene la información necesaria para crear individuos que representen 
 * soluciones del problema planteado en el enunciado. 
 * 
 */

public class PopulationFactoryP2 implements PopulationFactory{
	private double xmin=-512;
	private double xmax=512;
	private double TOL;
	
	public PopulationFactoryP2(double TOL) {
		this.TOL=TOL;
	}

	public Chromosome createChromosome() {
		return new ChromosomeP2(this.xmin, this.xmax, this.TOL,false);
	}	
	
	public Chromosome[] createPopulation(int size) {
		Chromosome[] result= new ChromosomeP2[size];
		for(int i=0; i<size; i++){
			result[i]=new ChromosomeP2(this.xmin,this.xmax,this.TOL,false);
		}
		return result;
	}
}
