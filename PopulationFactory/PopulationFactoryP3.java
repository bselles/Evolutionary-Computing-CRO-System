package PopulationFactory;

import Population.Chromosome;
import Population.ChromosomeP3;

/*
 * Implementación de la factoria que genera individuos (posibles 
 * soluciones/cromosomas) del problema 3.
 * 
 * Contiene la información necesaria para crear individuos que representen 
 * soluciones del problema planteado en el enunciado. 
 * 
 */

public class PopulationFactoryP3 implements PopulationFactory {

	private double[] xmin={-3.0,4.1};
	private double[] xmax={12.1,5.8};
	private double TOL;
	
	public PopulationFactoryP3(double TOL) {
		this.TOL=TOL;
	}

	public Chromosome createChromosome() {
		return new ChromosomeP3(this.xmin, this.xmax, this.TOL,true);
	}	
	
	public Chromosome[] createPopulation(int size) {
		Chromosome[] result= new ChromosomeP3[size];
		for(int i=0; i<size; i++){
			result[i]=new ChromosomeP3(this.xmin,this.xmax,this.TOL,true);
		}
		return result;
	}

}
