package PopulationFactory;

import Population.Chromosome;
import Population.ChromosomeP1;

/*
 * Implementación de la factoria que genera individuos (posibles 
 * soluciones/cromosomas) del problema 1.
 * 
 * Contiene la información necesaria para crear individuos que representen 
 * soluciones del problema planteado en el enunciado. 
 * 
 */
public class PopulationFactoryP1 implements PopulationFactory{
	private int xmin=0;
	private int xmax=32;
	private double TOL;
	
	public PopulationFactoryP1(double TOL) {
		this.TOL=TOL;
	}

	public Chromosome createChromosome() {
		return new ChromosomeP1(this.xmin, this.xmax, this.TOL,true);
	}

	public Chromosome[] createPopulation(int size) {
		Chromosome[] result= new ChromosomeP1[size];
		for(int i=0; i<size; i++){
			result[i]=new ChromosomeP1(this.xmin,this.xmax,this.TOL,true);
		}
		return result;
	}
}
