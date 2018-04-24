package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Torneo implements Seleccion {
   
    private int TamGrupoTorneo; 
    
    public Torneo(int tamGrupoTorneo) {
		TamGrupoTorneo = tamGrupoTorneo;
	}
    
	@Override
    public ArrayList<Individuo> selecciona(ArrayList<Individuo> pob, boolean minimizar) {
       
        double mejor_fitness;
        Individuo mejor;
        int tamPob = pob.size();
        ArrayList<Individuo> pob_selec = new ArrayList<Individuo>();
        Random aleatorio = new Random(System.currentTimeMillis());
        int rand;

       
        for(int i=0; i<tamPob; i++){
        	rand = aleatorio.nextInt(tamPob);
            mejor = pob.get(rand);
            mejor_fitness = pob.get(rand).getFitness();
            
            if(minimizar)
            	for(int j=1; j<TamGrupoTorneo; j++){
                    rand = aleatorio.nextInt(tamPob);
                    if(pob.get(rand).getFitness() < mejor_fitness){
                        mejor = pob.get(rand);
                        mejor_fitness = pob.get(rand).getFitness();
                    }
                   
                }
            else
	            for(int j=1; j<TamGrupoTorneo; j++){
	                rand = aleatorio.nextInt(tamPob);
	                if(pob.get(rand).getFitness() > mejor_fitness){
	                    mejor = pob.get(rand);
	                    mejor_fitness = pob.get(rand).getFitness();
	                }
	               
	            }
           
            pob_selec.add(mejor.clone());   
        }
       
        return pob_selec;
    }

}