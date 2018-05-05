package Mutation;


import Population.Chromosome;
import Population.ChromosomeP1;
import Population.RealGen;

public class RealMutation {
	
	public static Chromosome mutate(Chromosome a, int tipo, double prob, double desviacion){
		
		RealGen genAux;
		Chromosome result=a.getCopy();
		
		switch (tipo) {
		case 1: //Mutacion uniforme
			for (int i =0; i< result.getNumGens(); i++ ){
				if(Math.random() < prob){
					genAux = (RealGen) result.getGen(i).getCopy();
					genAux.setPhenotype(  result.getGen(i).getXmin() + Math.random() *
							Math.abs(result.getGen(i).getXmin()-result.getGen(i).getXmax()));
					result.setGen(i, genAux);
				}
			}	
			break;

		case 2: //Mutacion no uniforme
			double x = Math.random() * 3,res;

			if(Math.random() > prob)  //no se muta
				break;
			for (int i =0; i< result.getNumGens(); i++ ) { //Cuidado dominio de la funcion
				
				genAux = (RealGen) result.getGen(i).getCopy();
				/**************CAMBIADO*************/
				//TODO
				if(Math.random() > 0.5 ){
					 res = calculazacum(x,1) + genAux.getPhenotype();
					 if(res > result.getGen(i).getXmax())genAux.setPhenotype(result.getGen(i).getXmax());
					 else genAux.setPhenotype(res);
				}
				else{
					 res = genAux.getPhenotype() - calculazacum(x,1) ;
					 if(res < result.getGen(i).getXmin())genAux.setPhenotype(result.getGen(i).getXmin());
					 else genAux.setPhenotype(res);
				}
				result.setGen(i, genAux);
			}
			
			break;
			
		default: //opcion no reconocida -> se queda como esta
			System.out.print("Mutación no válida.");
		}
		return result;
	}
	
	
	private static double calculazacum(double x, double desviacion){  //Funcion de densidad de probabilidad normal
		double res;
		res = Math.pow((x/desviacion), 2);
		res *= (-0.5) ;
		res = Math.exp(res);
		res *= (1/(desviacion*(Math.sqrt(2*Math.PI)))); 
		return res;
	}

	
	public static void main(String[] args){
		for(int i=0; i<10; i++){
			Chromosome c= new ChromosomeP1();
			System.out.println(c.toString());
			c=RealMutation.mutate(c, 2, 1, 0);
			System.out.println(c.toString());
		}
	}
	
}
