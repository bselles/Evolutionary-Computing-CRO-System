package CrossingAlgorithms;

import Population.BinaryGen;
import Population.Chromosome;


/*Implementación algoritmo "Cruce Monopunto":

-Dado dos cromosomas, selecciona un punto de cruce aleatorio y los cruza.
-En el caso de que el cromosoma tenga varios genes, se selecciona un gen de forma
aleatoria para cruzarlo, de forma que solo se cruza un solo gen.
-Existe una probabilidad (crossP) de que este cruce se efectue. En el caso de 
que se genere un número aleatorio menor que crossP, no se efectuará el cruce.
*/

//Algoritmo de cruce para genes binarios (hereda de BinaryCrossing).
public class BinaryMonocross extends CrossingAlgorithm{

	public BinaryMonocross(double crossP){
		this.crossP=crossP;
	}
	
	public Chromosome[] crossChromosome( Chromosome c1, Chromosome c2){
		Chromosome[] result= new Chromosome[2];
		result[0]=c1.getCopy();
		result[1]=c2.getCopy();
		boolean tmp;
		//Gen que se cruzará.
		int cg=chooseCrossingGen(result[0].getNumGens());

		//Punto de cruce
		int cp=chooseCrossingPoint(((BinaryGen)result[0].getGen(cg)).getBits().length);
		
		for(int i=0; i<((BinaryGen)result[0].getGen(cg)).getBits().length; i++){
			if(i>=cp){
				tmp=((BinaryGen)result[0].getGen(cg)).getBits()[i];
				((BinaryGen)result[0].getGen(cg)).getBits()[i]=((BinaryGen)result[1].getGen(cg)).getBits()[i];
				((BinaryGen)result[1].getGen(cg)).getBits()[i]=tmp;
			}		
		}
		return result;
	}
	
	public Chromosome[] crossPopulation(Chromosome[] p){
		Chromosome[] result= new Chromosome[p.length];
		Chromosome[] cr;
		for(int i=0; i<p.length/2; i++){
			double rand= generateRandomDoubleNum();
			if(rand<=this.crossP){				
				cr=crossChromosome(p[2*i],p[2*i+1]);
				result[2*i]=cr[0].getCopy();
				result[2*i+1]=cr[1].getCopy();
			}else{
				result[2*i]=p[2*i].getCopy();
				result[2*i+1]=p[2*i+1].getCopy();
			}
		}
		return result;
	}	
}
