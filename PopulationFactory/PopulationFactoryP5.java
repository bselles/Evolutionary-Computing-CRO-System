package PopulationFactory;

import Population.Chromosome;
import Population.ChromosomeP5;

/*
 * Implementación de la factoria que genera individuos (posibles 
 * soluciones/cromosomas) del problema 5.
 * 
 * Contiene la información necesaria para crear individuos que representen 
 * soluciones del problema planteado en el enunciado. 
 * 
 */

public class PopulationFactoryP5 implements PopulationFactory {

	private int xmin=-10;
	private int xmax=10;
	private double TOL;
	private int n;
	
	public PopulationFactoryP5(double TOL, int n) {
		this.TOL=TOL;
		this.n=n;
	}

	public Chromosome createChromosome() {
		return new ChromosomeP5(this.xmin, this.xmax, this.TOL,this.n,false);
	}
	
	public Chromosome[] createPopulation(int size) {
		Chromosome[] result= new ChromosomeP5[size];
		for(int i=0; i<size; i++){
			result[i]=new ChromosomeP5(this.xmin,this.xmax,this.TOL, this.n,false);
		}
		return result;
	}
}
