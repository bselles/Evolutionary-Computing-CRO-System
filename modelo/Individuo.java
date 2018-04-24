package modelo;


public interface Individuo {

	//public ArrayList<?> getRepresentation();

	public double getFitness();

	void setFitness(double fitness2);
	
	public void muta(int tipo, double prob, double num);
	
	public Individuo clone();

}
 