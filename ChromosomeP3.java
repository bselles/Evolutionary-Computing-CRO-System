package Population;


/*
 * Implementación del cromosoma (posible solución) para el problema 3.
 */

public class ChromosomeP3 extends Chromosome{

	/**************************************************************************
	 * CONSTRUCTORES
	 **************************************************************************/
	public ChromosomeP3(){}
	
	public ChromosomeP3(double TOL){
		super();
		//Crearemos dos genes, tantas como las variables del problema.
		this.genotype=new RealGen[2];
		this.genotype[0]= new RealGen(-3, 12.1,TOL);
		this.genotype[1]= new RealGen(4.1, 5.8,TOL);
		this.maximizacion=true;
	}
	
	/**************************************************************************
	 * MÉTODOS PRIVADOS
	 **************************************************************************/
	protected double calculateFitness() {
		//En genotype[0] tendremos x1 y en genotype[1] x2.
		return 21.5 +
				this.genotype[0].getPhenotype()*
				Math.sin(4*Math.PI*this.genotype[0].getPhenotype())+
				this.genotype[1].getPhenotype()*
				Math.sin(20*Math.PI*this.genotype[1].getPhenotype());
	}
	/**************************************************************************
	 * MÉTODOS PÚBLICOS
	 **************************************************************************/
	public Chromosome getCopy(){
		Chromosome result= new ChromosomeP3();
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
			
			if(Math.random() < prob){
					
					genAux = (RealGen) this.getGen(1).getCopy();
					genAux.setPhenotype(-3.0 + Math.random() * 15.1);
					a.setGen(1, genAux);
					
					genAux = (RealGen) this.getGen(2).getCopy();
					genAux.setPhenotype(4.1 + Math.random() * 1.7);
					a.setGen(2, genAux);
					
			}
			
				
					
			break;

		case 2: //Mutacion no uniforme
			double x, res;		
			int rand = (int) Math.random();
			if(Math.random() > prob)  //no se cruzan
				break;

			x = Math.random() * 3;
			genAux = (RealGen) this.getGen(1).getCopy();
			if(rand == 1){
				 res = calculazacum(x) + genAux.getPhenotype();
				 if(res > 12.1)genAux.setPhenotype(12.1);
				 else genAux.setPhenotype(res);
			}
			else{
				 res = calculazacum(x) - genAux.getPhenotype();
				 if(res < -3.0)genAux.setPhenotype(-3.0);
				 else genAux.setPhenotype(res);
			}

			a.setGen(1, genAux);		
			
			rand = (int) Math.random();
			x = Math.random() * 3;
			genAux = (RealGen) this.getGen(2).getCopy();
			if(rand == 1){
				 res = calculazacum(x) + genAux.getPhenotype();
				 if(res > 5.8)genAux.setPhenotype(5.8);
				 else genAux.setPhenotype(res);
			}
			else{
				 res = calculazacum(x) - genAux.getPhenotype();
				 if(res < 4.1)genAux.setPhenotype(4.1);
				 else genAux.setPhenotype(res);
			}

			a.setGen(2, genAux);
			
			break;

		}
		
		return a;
	}
}
