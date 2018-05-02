package Mutation;


import Population.Chromosome;
import Population.RealGen;

public class RealMutation {
	
	public static Chromosome mutate(Chromosome a, int tipo, double prob, 
			double desviacion){
		RealGen genAux;
		switch (tipo) {
		case 1: //Mutacion uniforme
			for (int i =0; i< a.getNumGens(); i++ ) {
				if(Math.random() < prob){
					
					genAux = (RealGen) a.getGen(i).getCopy();
					genAux.setPhenotype(  a.getGen(i).getXmin() + Math.random() *
							Math.abs(a.getGen(i).getXmin()-a.getGen(i).getXmax()));
					a.setGen(i, genAux);
					
				}
			}
				
					
			break;

		case 2: //Mutacion no uniforme
			double x = Math.random() * 3,res;
			int rand;

			if(Math.random() > prob)  //no se cruzan
				break;
			for (int i =0; i< a.getNumGens(); i++ ) { //Cuidado dominio de la funcion
				
				rand = (int) Math.random();
				
				genAux = (RealGen) a.getGen(i).getCopy();
				if(rand == 1){
					 res = calculazacum(x) + genAux.getPhenotype();
					 if(res > a.getGen(i).getXmax())genAux.setPhenotype(512);
					 else genAux.setPhenotype(res);
				}
				else{
					 res = calculazacum(x) - genAux.getPhenotype();
					 if(res < a.getGen(i).getXmin())genAux.setPhenotype(-512);
					 else genAux.setPhenotype(res);
				}

				a.setGen(i, genAux);
			}
			
			break;
			
		default: //opcion no reconocida -> se queda como esta
			return a;
		}
		
		return a;
	}
	
	private static double calculaz(double v){ //funcion de densidad de probabilidad normal
		double N=Math.exp(-Math.pow(v, 2)/2)/Math.sqrt(2*Math.PI);
		return N;
	}
	
	private static double calculazacum(double v){  //Funcion de distribucion de probabilidad normal
		double acumulador = 0.00000028666;
		for(double i=-5;i<v;i=i+0.00001){
			acumulador = acumulador + (0.00001 * calculaz(i-0.000005));
		}
		return acumulador;
	}

}
