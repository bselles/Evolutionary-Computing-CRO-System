package Population;

public class ChromosomeP5 extends Chromosome{
	
	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP5(){}
	
	public ChromosomeP5(double TOL, int n){
		super();
		this.genotype=new RealGen[n];
		//Crearemos tantos genes como variables tenga el problema.
		for (int i=0; i<n; i++){
			this.genotype[i]= new RealGen(0, Math.PI,TOL);
		}
		this.maximizacion=false; //Problema de minimización.
	}

	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		double result=0;		
		for(int i=0;i<this.genotype.length; i++){
			result=result+Math.sin(this.genotype[i].getPhenotype())*
			Math.pow(Math.sin((i+1)*(Math.pow(this.genotype[i].getPhenotype(),2)/Math.PI)), 20);					
		}	
		return -result;
	}	
	
	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	public Chromosome getCopy(){
		Chromosome result= new ChromosomeP5();
		result.maximizacion=this.maximizacion;
		result.genotype= new Gen[this.genotype.length];
		for(int i=0; i<this.genotype.length; i++){
			result.genotype[i]=this.genotype[i].getCopy();
		}
		result.fitness=this.fitness;
		return result;
	}
	
	public Chromosome mutation(int tipo, double prob, double desviacion){
		
		RealGen genAux;
		Chromosome a = this.getCopy();
		
		switch (tipo) {
		case 1: //Mutacion uniforme
			for (int i =0; i< this.getNumGens(); i++ ) {
				if(Math.random() < prob){
					
					genAux = (RealGen) this.getGen(i).getCopy();
					genAux.setPhenotype(Math.random() * Math.PI);
					a.setGen(i, genAux);
					
				}
			}
				
					
			break;

		case 2: //Mutacion no uniforme
			double x = Math.random() * 3, res;		
			int rand;
			
			
			if(Math.random() > prob)  //no se cruzan
				break;
			for (int i =0; i< this.getNumGens(); i++ ) { //Cuidado dominio de la funcion
			
				rand = (int) Math.random();
				
				genAux = (RealGen) this.getGen(i).getCopy();
				if(rand == 1){
					 res = calculazacum(x) + genAux.getPhenotype();
					 if(res > Math.PI)genAux.setPhenotype(Math.PI);
					 else genAux.setPhenotype(res);
				}
				else{
					 res = calculazacum(x) - genAux.getPhenotype();
					 if(res < 0)genAux.setPhenotype(0);
					 else genAux.setPhenotype(res);
				}

				a.setGen(i, genAux);	

			}
			
			break;
			
		

		}
		
		return a;

	}
}