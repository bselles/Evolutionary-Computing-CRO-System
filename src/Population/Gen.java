package Population;


/*
 * Clase abstracta que representa un gen. A cada variable 
 * de un problema se le asocia un gen.
 */
public abstract class Gen {
	public abstract double getPhenotype();
	public abstract String toString();	
	public abstract Gen getCopy();
	public abstract double getXmin();
	public abstract double getXmax();
	
	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	
	protected double getRandomDouble (double min, double max){
		return Math.random()*(max-min)+min;
	}
}
