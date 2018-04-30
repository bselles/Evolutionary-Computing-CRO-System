package Population;

public class RealGen implements Gen {

	/**************************************************************************
	 * ATRIBUTOS
	 **************************************************************************/
	private double xmin;			//Valor mínimo que puede tomar la variable.
	private double xmax;			//Valor máximo que puede tomar la variable.
	public double phenotype;		//Fenotipo asociado al gen.
	private double TOL;
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public RealGen(double xmin, double xmax, double TOL){
		this.xmax=xmax;
		this.xmin=xmin;
		this.TOL=TOL;
		//AÑADIR ASPECTOS OPORTUNOS.
		//ASOCIARLE UN VALOR A PHENOTYPE.
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
		RealGen result= new RealGen(this.xmin, this.xmax,this.TOL);
		result.phenotype=this.phenotype;
		return result;
	}	
}
