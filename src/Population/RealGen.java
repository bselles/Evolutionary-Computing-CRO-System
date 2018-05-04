package Population;

public class RealGen extends Gen {

	/**************************************************************************
	 * ATRIBUTOS
	 **************************************************************************/
	private double xmin;			//Valor mínimo que puede tomar la variable.
	private double xmax;			//Valor máximo que puede tomar la variable.
	public double phenotype;		//Fenotipo asociado al gen.
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public RealGen(double xmin, double xmax){
		this.xmax=xmax;
		this.xmin=xmin;
		this.phenotype = getRandomDouble(xmin, xmax);
	}
	
	public double getXmin() {
		return xmin;
	}

	public double getXmax() {
		return xmax;
	}

	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	@Override
	public double getPhenotype() {
		return this.phenotype;
	}
	
	public void setPhenotype(double phe) {
		this.phenotype = phe;
	}

	@Override
	public String toString() {
		return Double.toString(this.phenotype);
	}
	
	@Override
	public Gen getCopy(){
		RealGen result= new RealGen(this.xmin, this.xmax);
		result.phenotype=this.phenotype;
		return result;
	}	
}
