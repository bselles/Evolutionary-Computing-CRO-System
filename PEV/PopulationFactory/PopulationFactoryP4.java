package PopulationFactory;

import Population.Chromosome;
import Population.ChromosomeP4;


/*
 * Implementación de la factoria que genera individuos (posibles 
 * soluciones/cromosomas) del problema 4.
 * 
 * Contiene la información necesaria para crear individuos que representen 
 * soluciones del problema planteado en el enunciado. 
 * 
 */

public class PopulationFactoryP4 implements PopulationFactory {

	private int xmin=-10;
	private int xmax=10;
	private double TOL;
	
	public PopulationFactoryP4(double TOL) {
		this.TOL=TOL;
	}

	public Chromosome createChromosome() {
		return new ChromosomeP4(this.xmin, this.xmax, this.TOL,false);
	}
	
	public Chromosome[] createPopulation(int size) {
		Chromosome[] result= new ChromosomeP4[size];
		for(int i=0; i<size; i++){
			result[i]=new ChromosomeP4(this.xmin,this.xmax,this.TOL,false);
		}
		return result;
	}
}
