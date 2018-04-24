package PopulationFactory;

import Population.Chromosome;

/*
 * Clase abstracta que representa una factoria de individuos (cromosomas/posibles soluciones)
 * para cada problema.
 * Todas las clases que heredan de esta son implementaciones de "generadores" de individuos
 * de cada problema.
 */

public interface PopulationFactory {
	public abstract Chromosome createChromosome(); 
	public abstract Chromosome[] createPopulation(int size);
}
