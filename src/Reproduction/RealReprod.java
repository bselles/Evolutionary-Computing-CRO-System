package Reproduction;

import java.util.ArrayList;

import Population.Chromosome;
import Population.ChromosomeP1;
import Population.RealGen;

public class RealReprod {
	
	public static ArrayList<Chromosome> reproduce(Chromosome a, Chromosome b, double probCruce, int tipo) { 
		ArrayList<Chromosome> hijos = new ArrayList<Chromosome>();
		Chromosome hijoA = a.getCopy(), hijoB = b.getCopy();
		
		int numGens = a.getNumGens();
		
		int punto, i;
		double alfa, valorA, valorB;
		
		RealGen genAux;
		
		switch (tipo) {
		case 1: //un punto
			if(Math.random() > probCruce)  //no se cruzan
				break;
			
			punto = (int) (Math.random() * numGens);
			for(i = punto; i<numGens; i++) {
				hijoA.setGen(i, b.getGen(i));
				hijoB.setGen(i, a.getGen(i));
			}
			
			break;
		case 2: //discreto uniforme
			punto = (int) (Math.random() * numGens);
			for(i = 0; i < numGens; i++) 
				if(Math.random() < probCruce) { 
					hijoA.setGen(i, b.getGen(i));
					hijoB.setGen(i, a.getGen(i));
				}

			break;
			
		case 3: //aritmetico
			if(Math.random() > probCruce)  //no se cruzan
				break;
			
			alfa = Math.random();
			for(i = 0; i< numGens; i++) {
				valorA = a.getGen(i).getPhenotype();
				valorB = b.getGen(i).getPhenotype();
				
				genAux = (RealGen) a.getGen(i).getCopy();
				genAux.setPhenotype( valorA * alfa + valorB * (1-alfa) );
				
				if(genAux.getPhenotype() > genAux.getXmax())
					genAux.setPhenotype(genAux.getXmax());
				else if (genAux.getPhenotype() < genAux.getXmin())
					genAux.setPhenotype(genAux.getXmin());
				
				hijoA.setGen(i, genAux);
				
				genAux = (RealGen) b.getGen(i).getCopy();
				genAux.setPhenotype( valorB * alfa + valorA * (1-alfa) );
				
				if(genAux.getPhenotype() > genAux.getXmax())
					genAux.setPhenotype(genAux.getXmax());
				else if (genAux.getPhenotype() < genAux.getXmin())
					genAux.setPhenotype(genAux.getXmin());
				
				hijoB.setGen(i, genAux);

			}
			break;
		case 4: //Simulated Binary CrossOver )
			if(Math.random() > probCruce)  //no se cruzan
				break;
			
			alfa = Math.random();
			double n = Math.random() + 1;
			double beta = alfa < 0.5 ? 2.0 * Math.pow(alfa, 1.0/(n+1.0) ) : Math.pow( 1.0 / (2.0 - 2.0*alfa) , 1.0/(n+1.0) );
			for(i = 0; i< numGens; i++) {
				valorA = a.getGen(i).getPhenotype();
				valorB = b.getGen(i).getPhenotype();
				
				genAux = (RealGen) a.getGen(i).getCopy();
				genAux.setPhenotype( 0.5 * (valorA + valorB - beta * Math.abs(valorB - valorA)) );
				
				if(genAux.getPhenotype() > genAux.getXmax())
					genAux.setPhenotype(genAux.getXmax());
				else if (genAux.getPhenotype() < genAux.getXmin())
					genAux.setPhenotype(genAux.getXmin());
				
				hijoA.setGen(i, genAux);
				
				genAux = (RealGen) b.getGen(i).getCopy();
				genAux.setPhenotype( 0.5 * (valorA + valorB + beta * Math.abs(valorB - valorA)) );
				
				if(genAux.getPhenotype() > genAux.getXmax())
					genAux.setPhenotype(genAux.getXmax());
				else if (genAux.getPhenotype() < genAux.getXmin())
					genAux.setPhenotype(genAux.getXmin());
				
				hijoB.setGen(i, genAux);
			}
			break;
				
		default:
			break;
		}
		
		hijos.add(hijoA);
		hijos.add(hijoB);
		return hijos;
	}
	
	/*
	public static void main(String[] args){
		Chromosome c1= new ChromosomeP1(0.01);
		Chromosome c2= new ChromosomeP1(0.01);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		ArrayList<Chromosome> result=RealReprod.reproduce(c1, c2, 1, 4);
		c1=result.get(0);
		c2=result.get(1);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
	}*/
}
