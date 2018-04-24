package modelo;

import java.util.ArrayList;

public class Estocastico implements Seleccion {

	@Override
	public ArrayList<Individuo> selecciona(ArrayList<Individuo> pob, boolean minimizar) {
		
		double[] probabilidadesAc = new double[pob.size()];
		int fitnessTotal = 0;
		ArrayList<Individuo> pob_selec = new ArrayList<Individuo>();
		double distancia_marcas = (double)1/(double)pob.size();
		double marca = distancia_marcas;
		
		if(minimizar){
			double max = 0.0;
			for(int i=0; i<pob.size();i++) 
				if(pob.get(i).getFitness() > max)
					max = pob.get(i).getFitness();
			
			for(int i=0; i<pob.size();i++) 
				fitnessTotal += max - pob.get(i).getFitness();
			
			probabilidadesAc[0] = ( max - pob.get(0).getFitness()) / fitnessTotal; 
			
			for(int i=1; i<pob.size(); i++) 
				probabilidadesAc[i] = probabilidadesAc[i-1] + ( max - pob.get(i).getFitness()) / fitnessTotal;
		} else {
			for(int i=0; i<pob.size();i++) 
				fitnessTotal += pob.get(i).getFitness();
			
			probabilidadesAc[0] = pob.get(0).getFitness() / fitnessTotal; 
			
			for(int i=1; i<pob.size(); i++) 
				probabilidadesAc[i] = probabilidadesAc[i-1] + pob.get(i).getFitness() / fitnessTotal;
		}
		for(int i=0; i<pob.size(); i++) {
			for(int j=0; j<pob.size(); j++) 
				if(marca <= probabilidadesAc[j]) {
					pob_selec.add(pob.get(j).clone());
					break;
				}
			marca += distancia_marcas;
		}
		
		
		return pob_selec;
	}

}
