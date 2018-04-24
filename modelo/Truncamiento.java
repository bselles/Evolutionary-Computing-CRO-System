package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Truncamiento implements Seleccion {
	
	private double Trunc ; 
	
	public Truncamiento(double trunc) {
		Trunc = trunc;
	}

	@Override
	public ArrayList<Individuo> selecciona(ArrayList<Individuo> pob, boolean minimizar) {
		
		ArrayList<Individuo> pob_selec = new ArrayList<Individuo>();
		pob.addAll(ordena(pob, minimizar));
		
		int p = (int) (1 / Trunc);
		int tamElite = pob.size()/p;
		
		//Hay veces que tamElite queda a 0 porque la poblacion es muy pequeña.
		if(tamElite==0)tamElite=1;
		
		//Hay que modificar ligeramente el algoritmo con repecto a la teoría para rellenar la poblacion con el número
		//adecuado de individuos
		while(pob_selec.size() < pob.size())
			for(int j=0; j<tamElite; j++)
				if(pob_selec.size() < pob.size())
					pob_selec.add(pob.get(j).clone());

		return pob_selec;
	}
	
	private ArrayList<Individuo> ordena(ArrayList<Individuo> pob, boolean minimizar){
		
	
		Collections.sort(pob, new Comparator<Individuo>(){

			@Override
			public int compare(Individuo o1, Individuo o2) {
				Double d1,d2;
				d1 = o2.getFitness();
				d2 = o1.getFitness();
				if(minimizar)
					return d2.compareTo(d1);
				else
					return d1.compareTo(d2);
			}
				
		});
		
		return pob ;
		
	}
	
	
	
	
}
